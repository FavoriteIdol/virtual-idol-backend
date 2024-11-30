package com.outsider.virtual.user.command.application.service;

import com.outsider.virtual.user.command.application.dto.SignUpRequestDTO;
import com.outsider.virtual.user.command.application.dto.UserInfoRequestDTO;
import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.aggregate.embeded.Authority;
import com.outsider.virtual.user.command.domain.aggregate.embeded.ProviderInfo;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;
import com.outsider.virtual.user.exception.DuplicateIdException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistUserService {

    private final UserCommandRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegistUserService(UserCommandRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Long registUser(UserInfoRequestDTO userRegistRequestDTO) {
        // 중복된 사용자가 있는지 확인
        if (userRepository.findByEmail(userRegistRequestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this ID already exists");
        }
        // 새로운 사용자 생성
        User newUser = new User(
                userRegistRequestDTO.getEmail(),
                userRegistRequestDTO.getPassword(),
                userRegistRequestDTO.getUserName(),
                userRegistRequestDTO.getUserImg(),
                Authority.ROLE_USER
        );
        // 사용자 저장
        return userRepository.save(newUser).getId();
    }
    @Transactional
    public Long registUser(SignUpRequestDTO userRegistRequestDTO) {
        // 중복된 사용자가 있는지 확인
        if (userRepository.findByEmail(userRegistRequestDTO.getEmail()).isPresent()) {
            throw new DuplicateIdException("유저가 이미 존재합니다.");
        }
        // 새로운 사용자 생성
        User newUser = new User(
                userRegistRequestDTO.getEmail(),
                passwordEncoder.encode(userRegistRequestDTO.getPassword()),
                userRegistRequestDTO.getUserName(),
                userRegistRequestDTO.getUserImg(),
                Authority.ROLE_USER
        );
        // 사용자 저장
        return userRepository.save(newUser).getId();
    }
}
