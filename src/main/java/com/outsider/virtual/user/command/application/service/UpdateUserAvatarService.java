package com.outsider.virtual.user.command.application.service;

import com.outsider.virtual.user.command.application.dto.UserUpdateRequestDTO;
import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserAvatarService {
    private final UserCommandRepository userRepository;
    @Autowired
    public UpdateUserAvatarService(UserCommandRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public User update(Long userId, String avatar) {
        // 사용자 조회
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("유저를 찾을수 없습니다."));
        user.setAvatar(avatar);
        return userRepository.save(user);
    }
}
