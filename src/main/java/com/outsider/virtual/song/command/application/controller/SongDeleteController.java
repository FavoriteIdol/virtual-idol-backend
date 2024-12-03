package com.outsider.virtual.song.command.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outsider.virtual.song.command.application.service.SongDeleteService;
import com.outsider.virtual.user.dto.CustomUserInfoDTO;
import com.outsider.virtual.util.UserId;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/songs")
@Tag(name = "노래 삭제", description = "노래 삭제를 위한 API")
public class SongDeleteController {
    private final SongDeleteService songDeleteService;

    public SongDeleteController(SongDeleteService songDeleteService) {
        this.songDeleteService = songDeleteService;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "노래 삭제", description = "노래를 삭제합니다.")
    public ResponseEntity<Void> deleteSong(
        @PathVariable Long id,
        @Parameter(hidden = true) @UserId CustomUserInfoDTO userInfoDTO
    ) {
        songDeleteService.delete(userInfoDTO.getUserId(), id);
        return ResponseEntity.ok().build();
    }
} 