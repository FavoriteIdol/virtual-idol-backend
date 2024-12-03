package com.outsider.virtual.song.command.application.controller;

import com.outsider.virtual.song.command.application.dto.SongCreateDTO;
import com.outsider.virtual.song.command.application.service.SongCreateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/songs")
@Tag(name = "노래 관리", description = "노래 등록 API")
public class SongCommandController {

    private final SongCreateService songCreateService;

    public SongCommandController(SongCreateService songCreateService) {
        this.songCreateService = songCreateService;
    }

    @PostMapping
    @Operation(summary = "노래 등록", description = "새로운 노래를 등록합니다.")
    public ResponseEntity<Long> register(@RequestBody SongCreateDTO dto) {
        Long songId = songCreateService.register(dto);
        return ResponseEntity.ok(songId);
    }
} 