package com.outsider.virtual.user.command.application.dto;

import lombok.*;

@Data
public class EmailAuthDTO {
    private String code;
    private Boolean flag;
}
