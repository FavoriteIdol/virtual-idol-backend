package com.outsider.virtual.user.dto;


import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.aggregate.embeded.Authority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomUserInfoDTO {
    private Long userId;
    private String email;
    private Authority role;
    private String username;

    public CustomUserInfoDTO() {
    }

    public CustomUserInfoDTO(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.role = user.getAuthority();
        this.username= user.getUserName();
    }
}
