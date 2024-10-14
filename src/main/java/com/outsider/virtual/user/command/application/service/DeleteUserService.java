package com.outsider.virtual.user.command.application.service;


import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService {

    private final UserCommandRepository userRepository;

    @Autowired
    public DeleteUserService(UserCommandRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setWithdrawal(true); // 소프트 딜리트를 위해 isWithdrawal 필드를 true로 설정
        userRepository.save(user);  // 변경사항을 저장
    }
}
