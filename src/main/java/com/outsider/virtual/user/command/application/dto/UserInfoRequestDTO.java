package com.outsider.virtual.user.command.application.dto;

import com.outsider.virtual.user.command.domain.aggregate.embeded.Authority;
import com.outsider.virtual.user.command.domain.aggregate.embeded.Gender;
import com.outsider.virtual.user.command.domain.aggregate.embeded.Location;
import lombok.Data;

@Data
public class UserInfoRequestDTO {

    private String email;
    private String password;
    private String userName;
    private Authority authority;
    private String token;

    public UserInfoRequestDTO(String email, String password, String userName, Authority authority) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.authority = authority;
    }

}
