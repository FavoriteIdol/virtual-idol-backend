package com.outsider.virtual.character.query.application.dto;

import lombok.Data;

@Data
public class CharacterDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String modelUrl;
    private boolean isActive;
} 