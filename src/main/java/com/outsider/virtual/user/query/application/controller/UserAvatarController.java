package com.outsider.virtual.user.query.application.controller;

import com.outsider.virtual.user.command.application.dto.UserAvatarDTO;
import com.outsider.virtual.user.dto.CustomUserInfoDTO;
import com.outsider.virtual.user.exception.NotAuthenticationException;
import com.outsider.virtual.user.query.application.service.UserAvatarService;
import com.outsider.virtual.util.UserId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/avatar")
public class UserAvatarController {

    private final UserAvatarService userAvatarService;


    public UserAvatarController(UserAvatarService userAvatarService) {
        this.userAvatarService = userAvatarService;
    }
    @GetMapping()
    public ResponseEntity<UserAvatarDTO> getAvatar(@UserId CustomUserInfoDTO userInfoDTO)
    {
            UserAvatarDTO userAvatarDTO= userAvatarService.getAvatar(userInfoDTO.getUserId());
            return ResponseEntity.ok(userAvatarDTO);

    }
}
