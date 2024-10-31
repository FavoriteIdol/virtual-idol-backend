package com.outsider.virtual.concert.query.application.dto;

import lombok.Data;

@Data
public class CreateResponseDTO {
    private String message;
    private Long id;

    // 생성자
    public CreateResponseDTO(String message, Long id) {
        this.message = message;
        this.id = id;
    }

}
