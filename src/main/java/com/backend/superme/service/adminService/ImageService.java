package com.backend.superme.service.adminService;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.backend.superme.config.s3Config.S3Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

    private S3Config s3Config;

    @Autowired
    public ImageService(S3Config s3Config) {

        this.s3Config = s3Config;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private String localLocation = "C:\\Users\\jinsu\\OneDrive\\바탕 화면\\localPath\\";

    public String imageUpload(MultipartRequest request) throws IOException {

        MultipartFile file = request.getFile("upload");

        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.indexOf("."));

        String uuidFileName = UUID.randomUUID() + ext;
        String localPath = localLocation + uuidFileName;
        //서버환경 이미지 저장
        File localFile = new File(localPath);
        file.transferTo(localFile);

        //s3 이미지 올림
        s3Config.amazonS3Client().putObject(new PutObjectRequest(bucket, uuidFileName, localFile).withCannedAcl(CannedAccessControlList.PublicRead));
        String s3Url = s3Config.amazonS3Client().getUrl(bucket, uuidFileName).toString();

        //서버에 저장한 이미지를 삭제
        localFile.delete();

        return s3Url;

    }



    // 이미지 삭제 메서드
    public void deleteImage(String imageUrl) {
        try {
            // 이미지 URL에서 파일 이름 추출
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

            // S3에서 해당 파일 삭제
            s3Config.amazonS3Client().deleteObject(bucket, fileName);
        } catch (AmazonServiceException e) {
            // S3에서 이미지를 삭제하는 도중 예외가 발생한 경우 처리
            e.printStackTrace();
        }
    }
}
