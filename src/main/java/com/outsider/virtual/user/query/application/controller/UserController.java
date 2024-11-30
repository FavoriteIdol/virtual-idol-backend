package com.outsider.virtual.user.query.application.controller;

import com.outsider.virtual.user.query.application.dto.UserMapper;
import com.outsider.virtual.user.query.application.dto.UserResponseDTO;
import com.outsider.virtual.user.query.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search/user")
public class UserController {

    private final UserService userService;



        @GetMapping("/displayName")
        public Page<UserResponseDTO> searchUsers(
                @RequestParam("displayName") String displayName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
            Pageable pageable = PageRequest.of(page, size);
            return userService.searchUsersByDisplayName(displayName, pageable);
        }
}
