package com.outsider.virtual.user.command.application.service;

import com.outsider.virtual.user.command.application.dto.UserUpdateRequestDTO;
import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserService {
    private final UserCommandRepository userRepository;
    @Autowired
    public UpdateUserService(UserCommandRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public User updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {
        // 사용자 조회
        Optional<User> optionalUser = userRepository.findById(userUpdateRequestDTO.getUserId());
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("User not found"));
        // 각 필드 업데이트 (null이 아닌 값만 업데이트)
        if (userUpdateRequestDTO.getPassword() != null) {
            user.setPassword(userUpdateRequestDTO.getPassword());
        }
        if (userUpdateRequestDTO.getUserName() != null) {
            user.setUsername(userUpdateRequestDTO.getUserName());
        }
        // 사용자 저장
        return userRepository.save(user);
    }
}
