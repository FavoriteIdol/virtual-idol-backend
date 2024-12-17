package com.outsider.virtual.user.query.dto;

import com.outsider.virtual.user.command.domain.aggregate.embeded.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String email;
    private String userName;
    private String userImg;
    private String avatar;
    private Authority authority;
} 