package com.outsider.virtual.user.query.controller;

import com.outsider.virtual.user.query.dto.UserDTO;
import com.outsider.virtual.user.query.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "사용자 조회", description = "사용자 정보 조회 API")
public class UserQueryController {

    private final UserQueryService userQueryService;

    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "사용자 정보 조회", description = "사용자 ID로 사용자 정보를 조회합니다.")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDTO = userQueryService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }
} 