package com.outsider.virtual.character.command.application.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CharacterUpdateDTO {
    private String name;
    private String description;
    private MultipartFile image;
    private MultipartFile model;
    private Boolean isActive;
} 