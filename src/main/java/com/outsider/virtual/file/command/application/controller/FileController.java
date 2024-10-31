package com.outsider.virtual.file.command.application.controller;

import com.outsider.virtual.file.command.application.service.MinioService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")
@Tag(name = "파일 업로드", description = "파일 업로드 및 URL 생성을 위한 API")
public class FileController {

    @Autowired
    private MinioService minioService;

    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    @Operation(summary = "파일 업로드", description = "사용자가 파일을 업로드하고, 서버에 저장합니다.")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
            return ResponseEntity.ok(minioService.uploadFile(file));
    }

    @Hidden
    @GetMapping("/download")
    @Operation(summary = "파일 다운로드", description = "서버에서 파일을 다운로드합니다.")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("filename") String filename) throws Exception {
        InputStreamResource resource = new InputStreamResource(minioService.downloadFile(filename));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


    @GetMapping("/downloadByUrl")
    @Operation(summary = "파일 다운로드 (URL)", description = "파일 URL을 기반으로 서버에서 파일을 다운로드합니다.")
    public ResponseEntity<InputStreamResource> downloadFileByUrl(@RequestParam("fileUrl") String fileUrl) throws Exception {
        String filename = minioService.extractFilenameFromUrl(fileUrl);
        InputStreamResource resource = new InputStreamResource(minioService.downloadFile(filename));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
