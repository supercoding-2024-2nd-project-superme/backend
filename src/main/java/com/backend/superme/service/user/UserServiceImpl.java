package com.backend.superme.service.user;

import com.backend.superme.config.user.JwtTokenProvider;
import com.backend.superme.config.user.UserPrincipal;
import com.backend.superme.constant.user.StatusEnum;
import com.backend.superme.dto.user.UserDto;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.repository.user.UserRepository;
import com.backend.superme.repository.view.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.backend.superme.entity.view.QOrder.order;

@Service("userService")
public class UserServiceImpl implements UserService {
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    Boolean duplicateEmail = false;

    @Autowired
    public void ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public boolean checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            return duplicateEmail = true;
        } else {
            return duplicateEmail = false;
        }
    }

    @Override
    public void signupUser(UserDto userDto) {
        System.out.println(userDto.toString());
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        if (checkEmail(email)) {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        }

        String encryptedPassword = passwordEncoder.encode(password);

        // UserDto -> UserEntity로 변환
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(encryptedPassword);
        userEntity.setProfile(userDto.getProfile());
        userEntity.setNickname(userDto.getNickname());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setAddress(userDto.getAddress());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setGender(userDto.getGender());


        userEntity.setRole("USER");

        userRepository.save(userEntity);

    }


    @Override
    public String authenticateUser(UserDto userDto) {

        String email = userDto.getEmail();
        String password = userDto.getPassword();
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if (userEntityOptional.isEmpty()) {
            throw new RuntimeException("존재하지 않는 이메일입니다.");
        }

        UserEntity userEntity = userEntityOptional.get();

        // 회원 상태 확인
        if (userEntity.getStatus() == StatusEnum.DELETED) {
            throw new RuntimeException("탈퇴한 회원입니다.");
        }

        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        UserPrincipal userPrincipal = UserPrincipal.create(userEntity);
        String token = jwtTokenProvider.generateToken(userPrincipal);

        return token;

    }

    public String emailFromToken(String token) {
        //토큰 검증
        if (!jwtTokenProvider.validateJwtToken(token)) {
            throw new RuntimeException("Token is not valid");
        }

        return jwtTokenProvider.getEmailFromToken(token);
    }

    public void withdrawUser(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setStatus(StatusEnum.DELETED);
            userRepository.save(user);
        } else {
            throw new RuntimeException("해당 이메일을 가진 사용자를 찾을 수 없습니다.");
        }
    }

    @Override
    public Optional<UserEntity> findById(Long userId) {
        return userRepository.findById(userId);
    }

    // 사용자의 페이머니 잔액 조회 메서드
    @Override
    public BigDecimal getUserBalance(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get().getBalance();
        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
    }
    @Override
    public void deductBalance(Long userId, BigDecimal totalPrice) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            BigDecimal currentBalance = user.getBalance();
            if(currentBalance.compareTo(totalPrice) < 0) {
                throw new RuntimeException("잔액이 부족합니다.");
            }
            BigDecimal newBalance = currentBalance.subtract(totalPrice);
            user.setBalance(newBalance);
            userRepository.save(user);
        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
    }

    // 사용자의 페이머니 잔액 업데이트 메서드
    public void updateUserBalance(Long userId, BigDecimal newBalance) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setBalance(newBalance);
            userRepository.save(user);
        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
    }


}
