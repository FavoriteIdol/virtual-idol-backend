package com.outsider.virtual.util;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtil {

    public static String generateFileName(MultipartFile file) {
        // 원본 파일 이름을 가져옵니다.
        String originalFileName = file.getOriginalFilename();

        // 확장자 추출
        String extension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }



        // 파일 이름에서 확장자를 제외한 이름만 추출
        String fileNameWithoutExtension = originalFileName != null ? originalFileName.substring(0, originalFileName.lastIndexOf(".")) : "unknown";

        // 현재 시간 가져오기
        LocalDateTime now = LocalDateTime.now();

        // 날짜 형식 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = now.format(formatter);

        // 새로운 파일 이름 생성
        String newFileName = fileNameWithoutExtension + "_" + formattedDateTime + extension;

        return newFileName;
    }
}
