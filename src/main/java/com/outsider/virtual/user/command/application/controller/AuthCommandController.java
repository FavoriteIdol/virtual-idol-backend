package com.outsider.virtual.user.command.application.controller;

import com.outsider.virtual.user.command.application.dto.SignUpRequestDTO;
import com.outsider.virtual.user.command.application.service.RegistUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "유저 관리", description = "사용자 인증 및 회원가입 API")
public class AuthCommandController {

    private final RegistUserService registUserService;

    public AuthCommandController(RegistUserService registUserService) {
        this.registUserService = registUserService;
    }

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "사용자 정보를 받아 새로운 계정을 생성합니다.")
    public ResponseEntity<String> register(@RequestBody SignUpRequestDTO user) {
        registUserService.registUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공하셨습니다.");
    }
}
