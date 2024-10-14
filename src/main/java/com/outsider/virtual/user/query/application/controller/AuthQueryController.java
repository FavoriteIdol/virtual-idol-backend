package com.outsider.virtual.user.query.application.controller;

import com.outsider.virtual.user.command.application.dto.LoginRequestDTO;
import com.outsider.virtual.user.command.application.dto.UserInfoRequestDTO;
import com.outsider.virtual.user.command.application.service.AuthService;
import com.outsider.virtual.user.query.application.service.UserInfoService;
import com.outsider.virtual.util.UserId;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "유저 조회", description = "사용자 인증 및 정보 조회 API")
public class AuthQueryController {

    private final AuthService authService;
    private final UserInfoService userService;

    public AuthQueryController(AuthService authService, UserInfoService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @CrossOrigin(origins = "https://monitor.master-of-prediction.shop:3001", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자가 로그인하여 액세스 토큰을 받아옵니다. 쿠키에 토큰이 설정됩니다.")
    public ResponseEntity<UserInfoRequestDTO> postMemberProfile(
            @Valid @RequestBody LoginRequestDTO request,
            HttpServletResponse response
    ) throws IOException {

        String token = this.authService.login(request);

        // 쿠키 생성
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(false); // 클라이언트 측에서 자바스크립트로 접근 불가능하게 설정
        cookie.setSecure(true); // HTTPS 환경에서만 전송되도록 설정 (HTTPS를 사용하는 경우)
        cookie.setDomain("master-of-prediction.shop"); // 쿠키가 유효한 도메인 설정
        cookie.setPath("/"); // 쿠키가 유효한 경로 설정
        cookie.setAttribute("SameSite", "None");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 쿠키 유효 기간 설정 (7일 예시)
        response.addCookie(cookie);

        // 사용자 정보 조회
        String email = request.getEmail();
        Optional<UserInfoRequestDTO> userInfo = userService.getUserInfoByEmail(email);
        if (userInfo.isPresent()) {
            userInfo.get().setToken(token);
            return ResponseEntity.ok(userInfo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Hidden
    @GetMapping("/users")
    @Operation(summary = "사용자 정보 조회", description = "로그인한 사용자의 정보를 조회합니다.")
    public ResponseEntity<UserInfoRequestDTO> getMemberProfile(@UserId Long userId) throws IOException {
        Optional<UserInfoRequestDTO> userInfo = userService.getUserInfoById(userId);
        if (userInfo.isPresent()) {
            return ResponseEntity.ok(userInfo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
