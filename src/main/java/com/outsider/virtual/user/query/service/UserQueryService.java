package com.outsider.virtual.user.query.service;

import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;
import com.outsider.virtual.user.query.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserCommandRepository userRepository;

    public UserQueryService(UserCommandRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        
        return new UserDTO(
            user.getId(),
            user.getEmail(),
            user.getUserName(),
            user.getUserImg(),
            user.getAvatar(),
            user.getAuthority()
        );
    }
} 