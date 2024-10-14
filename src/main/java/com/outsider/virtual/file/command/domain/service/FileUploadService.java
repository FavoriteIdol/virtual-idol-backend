package com.outsider.virtual.file.command.domain.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadFile(MultipartFile file) throws Exception;
}
