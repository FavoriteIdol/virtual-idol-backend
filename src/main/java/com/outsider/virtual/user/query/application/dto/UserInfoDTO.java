package com.outsider.virtual.user.query.application.dto;


import lombok.Data;

@Data
public class UserInfoDTO {
    private Long userID;
    private String username;
    private String displayName;
    private String tier;
    private String userURL;
}
