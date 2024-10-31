package com.outsider.virtual.file.command.application.service;

import com.outsider.virtual.file.command.domain.service.FileUploadService;
import io.minio.*;
        import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class MinioService implements FileUploadService {

    @Autowired
    private MinioClient minioClient;
    @Value("${minio.url}")
    private String minioServerDomain;
    @Value("${minio.bucketName}")
    private String bucketName;

    public String uploadFile(MultipartFile file) throws Exception {
        // 버킷이 존재하는지 확인하고, 존재하지 않으면 생성
        StringBuilder sb = new StringBuilder();

        // 파일의 원래 이름에서 확장자 추출
        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // UUID만으로 파일 이름 생성, 확장자 추가
        sb.append(UUID.randomUUID()).append(extension);

        // 버킷이 존재하는지 확인하고, 존재하지 않으면 생성
        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!isExist) {
            // 버킷 생성
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());

            // 퍼블릭 정책 설정
            String policy = "{\n" +
                    "   \"Version\":\"2012-10-17\",\n" +
                    "   \"Statement\":[\n" +
                    "      {\n" +
                    "         \"Effect\":\"Allow\",\n" +
                    "         \"Principal\":\"*\",\n" +
                    "         \"Action\":[\"s3:GetObject\"],\n" +
                    "         \"Resource\":[\"arn:aws:s3:::" + bucketName + "/*\"]\n" +
                    "      }\n" +
                    "   ]\n" +
                    "}";

            // 정책 적용
            minioClient.setBucketPolicy(
                    SetBucketPolicyArgs.builder()
                            .bucket(bucketName)
                            .config(policy)
                            .build()
            );
        }

        // 파일 업로드
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(sb.toString()).stream(
                                file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());

        // 업로드된 파일의 URL 생성
        String fileUrl =  minioServerDomain + "/" + bucketName + "/" + sb.toString();
        return fileUrl;
    }
    public InputStream downloadFile(String filename) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .build());
    }
    // Method to extract filename from URL
    public String extractFilenameFromUrl(String fileUrl) throws Exception {
        // Assuming the URL structure is like: https://example.com/bucket-name/filename
        String decodedUrl = URLDecoder.decode(fileUrl, StandardCharsets.UTF_8);
        // Extract the last part of the URL, which should be the filename
        return decodedUrl.substring(decodedUrl.lastIndexOf("/") + 1);
    }

}
