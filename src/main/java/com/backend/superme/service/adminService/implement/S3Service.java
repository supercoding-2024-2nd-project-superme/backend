package com.backend.superme.service.adminService.implement;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.backend.superme.config.global.BusinessException;
import com.backend.superme.config.global.ErrorCode;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional //메소드 실행중에 예외가 발생하면 트랜잭션이 롤백됩니다.
@RequiredArgsConstructor
public class S3Service {

    // 서비스를 사용하기 위한 클라잉너트 객체를 의미, 이 객체는 AWS S3 와 통신하여 파일업로드, 다운로드, 삭제 등의 작업을 수행가능합니다.
    private final AmazonS3 s3Client;

    // AWS 액세스 키
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    // AWS 시크릿키
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    // AWS S3 버킷 이름
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // AWS 실행되는 지역
    @Value("${cloud.aws.region.static}")
    private String region;


    // 빈이 생성된 후에 호출되는 메서드임을 나타내는 어노테이션
    @PostConstruct
    public AmazonS3Client amazonS3Client() {
        // 액세스 키와 시크릿 키를 사용하여 AWS 자격 증명 객체를 생성
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

        // Amazon S3 클라이언트를 생성하는 빌더 객체를 생성
        // AmazonS3ClientBuilder는 AmazonS3 클라이언트를 생성하는 빌더 패턴을 구현한 클래스입니다.
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                //클라이언트 빌더에 지정된 지역 (Region) 을 설정
                .withRegion(region)
                // 클라이언트 빌더에 AWS 자격 증명을 제공
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                // 생성된 Amazon S3 클라이언트 객체를 반환
                .build();
    }

    //파일 업로드 처리 메서드
    public List<String> upload(List<MultipartFile> multipartFile) {
        //이미지 URL 을 저장할 리스트를 생성합니다.
        List<String> imgUrlList = new ArrayList<>();

        // 만약 multipartFile 이 null 이라면 필수 이미지가 제공되지 않았으므로 예외를 발생시킵니다.
        if (multipartFile == null) {
            throw new BusinessException(ErrorCode.REQUIRED_IMAGE);
        }

        // 전달된 multipartFile 리스트의 각 파일에 대해 반복합니다.
        //for 문 사용이유 : 각 파일의 업로드 처리, 개별로 파일처리를 위해서 for 문 사용
        for (MultipartFile file : multipartFile) {
            //파일 이름을 생성
            String fileName = createFileName(file.getOriginalFilename());
            //파일의 메타데이터를 생성합니다.
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()) {
                // S3에 파일을 업로드하고, 접근 권한을 PublicRead 로 설정합니다
                s3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
                //업로드 된 이미지의 URL 을 생성하고, 리스트에 추가합니다.
                String imgUrl = generateImgUrl(fileName);
                imgUrlList.add(imgUrl);
            } catch (IOException e) {
                // 파일 업로드 중에 예외가 발생하면 이미지 업로드 오류를 나타내는 예외를 발생시킵니다.
                throw new BusinessException(ErrorCode.UPLOAD_ERROR_IMAGE);
            }
        }
        // 모든 이미지의 URL 이 추가된 리스트를 반환합니다.
        return imgUrlList;
    }

    //파일 이름으로 S3에 업로드된 이미지 URL 생성
    private String generateImgUrl(String filename) {
        return "https://" + bucket + ".s3." + region + ".amazonaws.com/" + filename;
    }


    //이미지 파일명 중복방지
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    //파일 유효성 검사
    private String getFileExtension(String fileName) {
        if (fileName.length() == 0) {
            throw new BusinessException(ErrorCode.REQUIRED_IMAGE);
        }
        ArrayList<String> fileValidate = new ArrayList<>();
        fileValidate.add(".jpg");
        fileValidate.add(".jpeg");
        fileValidate.add(".png");
        fileValidate.add(".JPG");
        fileValidate.add(".JPEG");
        fileValidate.add(".PNG");
        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if (!fileValidate.contains(idxFileName)) {
            throw new BusinessException(ErrorCode.VALID_ERROR_IMAGE);
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    //DeleteObject 를 통해 S3 파일 삭제
    public void deleteFile(String fileName) {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, fileName);
        s3Client.deleteObject(deleteObjectRequest);
    }
}

