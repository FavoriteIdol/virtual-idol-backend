package com.outsider.virtual.file.command.application.controller;

import com.outsider.virtual.file.command.application.service.MinioService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Hidden
@RestController
@RequestMapping("/api/v1/files")
@Tag(name = "파일 업로드", description = "파일 업로드 및 URL 생성을 위한 API")
public class FileController {

    @Autowired
    private MinioService minioService;

    @PostMapping("/upload")
    @Operation(summary = "파일 업로드", description = "사용자가 파일을 업로드하고, 서버에 저장합니다.")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            minioService.uploadFile(file);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
        }
    }
}
