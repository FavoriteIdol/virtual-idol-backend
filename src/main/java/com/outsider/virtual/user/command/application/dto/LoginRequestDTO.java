package com.outsider.virtual.user.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "AUTH_REQ_01 : 로그인 요청 DTO")
public class LoginRequestDTO {
    @Schema(description = "사용자 이메일 주소", example = "user@example.com")
    @Email
    private String email;

    @Schema(description = "사용자 비밀번호", example = "password123")
    @NotNull(message = "패스워드 입력은 필수입니다.")
    private String password;
}
