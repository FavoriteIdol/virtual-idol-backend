package com.outsider.virtual.user.query.application.dto;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String email;
    private String userName;
    private Boolean isWithdrawal;
    private String userImg;

    // 생성자
    public UserResponseDTO(Long id, String email, String userName, Boolean isWithdrawal, String userImg) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.isWithdrawal = isWithdrawal;
        this.userImg = userImg;
    }

}
