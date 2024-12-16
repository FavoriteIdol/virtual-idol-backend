package com.outsider.virtual.character.command.application.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CharacterCreateDTO {
    private String name;
    private String description;
    private MultipartFile image;
    private MultipartFile model;
} 