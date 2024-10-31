package com.outsider.virtual.user.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(title = "AUTH_REQ_02 : 회원가입 요청 DTO")
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    @Schema(description = "사용자 이메일 주소", example = "user@example.com")
    @Email
    private String email;

    @Schema(description = "사용자 비밀번호", example = "password123")
    @NotNull(message = "패스워드 입력은 필수입니다.")
    private String password;
    @Schema(description = "사용자 이름", example = "홍길동")
    @NotNull(message = "사용자 이름 입력은 필수입니다.")
    private String userName;


}
