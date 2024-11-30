package com.outsider.virtual.user.query.application.service;

import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;
import com.outsider.virtual.user.query.application.dto.UserMapper;
import com.outsider.virtual.user.query.application.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserCommandRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    public UserService(UserCommandRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Page<UserResponseDTO> searchUsersByDisplayName(String displayName, Pageable pageable) {
        return userRepository.findByUserNameContaining(displayName, pageable)
                .map(userMapper::toUserResponseDTO);
    }
}
