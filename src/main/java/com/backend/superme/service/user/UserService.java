package com.backend.superme.service.user;

import com.backend.superme.dto.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void signupUser(UserDto userDto);

    boolean checkEmail(String email);

    String authenticateUser(UserDto userDto);

    String emailFromToken(String token);

    void withdrawUser(String email);

}

//UserService 인터페이스는 사용자 관련 서비스를 정의하고 있습니다.
// 이 서비스는 사용자의 회원가입, 이메일 중복 검사, 그리고 사용자 인증과 같은 핵심 기능을 포함합니다.
// @Service 어노테이션은 이 인터페이스가 서비스 계층의 컴포넌트임을 Spring Framework에 알립니다. 구체적인 메소드 설명은 다음과 같습니다.

//void signupUser(UserDto userDto)
//목적: 사용자 회원가입을 처리합니다.
//파라미터: UserDto - 사용자 정보를 담고 있는 데이터 전송 객체(DTO).
//설명: 이 메소드는 회원가입 요청 시 사용자로부터 받은 정보(UserDto)를 사용하여 새로운 사용자 계정을 생성합니다.
// 이 과정에서 사용자 정보의 유효성 검사, 비밀번호 해싱, 사용자 정보의 데이터베이스 저장 등의 작업이 수행될 수 있습니다.

//boolean checkEmail(String email)
//목적: 이메일 중복 검사를 수행합니다.
//파라미터: String - 검사할 이메일 주소.
//반환값: boolean - 이메일이 이미 사용 중인지 여부를 나타냅니다.
//설명: 이 메소드는 사용자가 입력한 이메일 주소가 이미 사용 중인지 여부를 검사합니다.
// 주로 회원가입 폼에서 이메일 입력 필드를 검증할 때 사용됩니다. 사용 중인 이메일인 경우 true를, 그렇지 않은 경우 false를 반환합니다.

//String authenticateUser(UserDto userDto)
//목적: 사용자 로그인을 처리하고 인증 토큰을 생성합니다.
//파라미터: UserDto - 로그인 요청에 사용된 사용자의 이메일과 비밀번호를 담고 있는 DTO.
//반환값: String - 생성된 JWT 인증 토큰. 인증 실패 시 null 또는 예외가 발생할 수 있습니다.
//설명: 사용자의 로그인 요청을 처리합니다.
// 사용자로부터 받은 이메일과 비밀번호를 검증하고, 성공적인 인증 후에는 JWT 토큰을 생성하여 반환합니다.
//이 토큰은 클라이언트가 이후 요청에서 사용할 수 있으며, 서버는 이 토큰을 통해 사용자의 인증 상태를 검증합니다.
//UserService 인터페이스는 사용자 관리와 인증 로직의 추상화를 제공합니다.
// 구현 클래스에서는 이 인터페이스를 구현하여 실제 로직을 완성해야 합니다.
// 이러한 추상화를 통해, 애플리케이션의 서비스 계층이 더욱 유연하고 테스트하기 쉬워집니다.