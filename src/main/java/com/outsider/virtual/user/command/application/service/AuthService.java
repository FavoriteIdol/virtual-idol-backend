package com.outsider.virtual.user.command.application.service;

import com.outsider.virtual.user.command.application.dto.LoginRequestDTO;
import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;
import com.outsider.virtual.user.dto.CustomUserInfoDTO;
import com.outsider.virtual.util.JwtUtil;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuthService {


    private final JwtUtil jwtUtil;
    private final UserCommandRepository userCommandRepository;
    private final BCryptPasswordEncoder encoder;


    public AuthService(JwtUtil jwtUtil, UserCommandRepository userCommandRepository, BCryptPasswordEncoder encoder) {
        this.jwtUtil = jwtUtil;
        this.userCommandRepository = userCommandRepository;
        this.encoder = encoder;
    }
    @Transactional
    public String login(LoginRequestDTO dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        Optional<User> user = userCommandRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");
        }else
        {
            checkWithdrawalStatus(user.get());
        }
        // 암호화된 password를 디코딩한 값과 입력한 패스워드 값이 다르면 null 반환
        if(!encoder.matches(password, user.get().getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        CustomUserInfoDTO info = new CustomUserInfoDTO(user.get()) ;

        String accessToken = jwtUtil.createAccessToken(info);
        return accessToken;
    }


    public void checkWithdrawalStatus(User user) {
        if (user.getWithdrawal()) {
            user.setWithdrawal(false);
        }
    }
}
