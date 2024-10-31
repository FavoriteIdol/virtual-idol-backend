package com.outsider.virtual.user.command.application.controller;

import com.outsider.virtual.user.command.application.dto.UserAvatarDTO;
import com.outsider.virtual.user.command.application.service.UpdateUserAvatarService;
import com.outsider.virtual.user.dto.CustomUserInfoDTO;
import com.outsider.virtual.user.exception.NotAuthenticationException;
import com.outsider.virtual.util.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/avatar")
@Tag(name = "유저 아바타 관리", description = "아바타 업데이트")
public class AvatarUpdateController {

    public final UpdateUserAvatarService updateUserAvatarService;

    public AvatarUpdateController(UpdateUserAvatarService updateUserAvatarService) {
        this.updateUserAvatarService = updateUserAvatarService;
    }
    @PostMapping()
    @Operation(summary = "아바타 업데이트", description = "아바타 업데이트.")
    ResponseEntity<?> update(@UserId CustomUserInfoDTO userInfoDTO , @RequestBody UserAvatarDTO userAvatarUpdateDTO)
    {
        updateUserAvatarService.update(userInfoDTO.getUserId(),userAvatarUpdateDTO.getAvatar());
        return ResponseEntity.ok().body("아바타 업데이트가 완료 되었습니다.");
    }

}
