package com.outsider.virtual.user.query.application.service;


import com.outsider.virtual.user.command.application.dto.UserAvatarDTO;
import com.outsider.virtual.user.command.application.dto.UserInfoRequestDTO;
import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAvatarService {

    private final UserCommandRepository userRepository;

    @Autowired
    public UserAvatarService(UserCommandRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to retrieve user by ID and map to UserInfoRequestDTO
    public UserAvatarDTO getAvatar(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent())
        {
            return userOptional.map(this::convertToDTO).get();

        }else
        {
            return null;

        }
    }

    // Private method to convert User entity to UserInfoRequestDTO
    private UserAvatarDTO convertToDTO(User user) {
        return new UserAvatarDTO(user.getAvatar()) ;
    }
}
