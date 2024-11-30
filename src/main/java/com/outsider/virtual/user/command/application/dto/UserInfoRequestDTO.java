package com.outsider.virtual.user.command.application.dto;

import com.outsider.virtual.user.command.domain.aggregate.embeded.Authority;
import com.outsider.virtual.user.command.domain.aggregate.embeded.Gender;
import com.outsider.virtual.user.command.domain.aggregate.embeded.Location;
import lombok.Data;

@Data
public class UserInfoRequestDTO {
    private Long userId;
    private String email;
    private String password;
    private String userName;
    private Authority authority;
    private String userImg;
    private String token;

    public UserInfoRequestDTO(Long userId,String email, String password, String userName,String userImg, Authority authority) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.userImg = userImg;
        this.authority = authority;
    }

}
