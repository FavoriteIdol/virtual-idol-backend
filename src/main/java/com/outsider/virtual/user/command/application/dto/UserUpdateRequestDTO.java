package com.outsider.virtual.user.command.application.dto;

import com.outsider.virtual.user.command.domain.aggregate.embeded.Gender;
import com.outsider.virtual.user.command.domain.aggregate.embeded.Location;
import lombok.Data;

@Data
public class UserUpdateRequestDTO {
    private Long userId;
    private String email;
    private String password;
    private String userName;
}
