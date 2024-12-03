package com.outsider.virtual.song.query.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outsider.virtual.song.query.application.dto.SongDTO;
import com.outsider.virtual.song.query.application.service.SongQueryService;
import com.outsider.virtual.user.dto.CustomUserInfoDTO;
import com.outsider.virtual.util.UserId;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/songs")
@Tag(name = "노래 조회", description = "노래 조회를 위한 API")
public class SongQueryController {

    private final SongQueryService songQueryService;

    public SongQueryController(SongQueryService songQueryService) {
        this.songQueryService = songQueryService;
    }

    @Operation(summary = "내 노래 목록 조회", description = "현재 로그인한 사용자의 노래 목록을 조회합니다.")
    @GetMapping("/my-songs")
    public ResponseEntity<List<SongDTO>> getMySongs(@Parameter(hidden = true) @UserId CustomUserInfoDTO userInfoDTO) {
        return ResponseEntity.ok(songQueryService.getSongsByUserId(userInfoDTO.getUserId()));
    }
} 