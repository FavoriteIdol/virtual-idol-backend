package com.outsider.virtual.config;

import com.outsider.virtual.user.command.infrastructure.service.CustomUserDetail;
import com.outsider.virtual.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        // JWT 토큰이 헤더에 있는 경우
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            // JWT 토큰의 유효성 검증
            log.info("JWT VALIDATE: {}",jwtUtil.validateToken(token));
            if (jwtUtil.validateToken(token)) {
                Long userId = jwtUtil.getUserId(token);
                String email = jwtUtil.getUserEmail(token);
                String userName = jwtUtil.getUserName(token);
                String role = jwtUtil.getRole(token);
                log.info("JWT VALIDATE userId: {}", userId);

                // CustomUserDetail 객체 생성
                UserDetails userDetails = new CustomUserDetail(userId, email, userName, role);

                // UsernamePasswordAuthenticationToken 생성
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // SecurityContextHolder에 인증 정보 설정
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 사용자 정보를 HTTP 헤더에 추가하여 다른 마이크로서비스로 전달
                String encodedUserName = URLEncoder.encode(userName, "UTF-8");
                request.setAttribute("X-User-Id", userId.toString());
                request.setAttribute("X-User-Email", email);
                request.setAttribute("X-User-Name", userName);
                request.setAttribute("X-User-Role", role);
                //TODO: MSA 헤더 User 정보 전파
                response.setHeader("X-User-Id", userId.toString());
                response.setHeader("X-User-Email", email);
                response.setHeader("X-User-Name", encodedUserName);
                response.setHeader("X-User-Role", role);
            }
        }

        filterChain.doFilter(request, response); // 다음 필터로 넘기기
    }
}
