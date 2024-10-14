package com.outsider.virtual.user.command.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserPointUpdateDTO {
    private Long userId;
    private BigDecimal point;

    public UserPointUpdateDTO(Long userId, BigDecimal point) {
        this.userId = userId;
        this.point = point;
    }
}
