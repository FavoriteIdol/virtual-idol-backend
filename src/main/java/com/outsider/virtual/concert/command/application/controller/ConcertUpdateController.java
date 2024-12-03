package com.outsider.virtual.concert.command.application.controller;

import com.outsider.virtual.concert.command.application.dto.ConcertUpdateDTO;
import com.outsider.virtual.concert.command.application.service.ConcertUpdateService;
import com.outsider.virtual.user.dto.CustomUserInfoDTO;
import com.outsider.virtual.util.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/concerts")
@Tag(name = "공연 수정", description = "공연 정보 수정을 위한 API")
public class ConcertUpdateController {

    private final ConcertUpdateService concertUpdateService;

    public ConcertUpdateController(ConcertUpdateService concertUpdateService) {
        this.concertUpdateService = concertUpdateService;
    }

    @Operation(summary = "공연 정보 수정", description = "공연의 정보를 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateConcert(
        @Parameter(hidden = true) @UserId CustomUserInfoDTO userInfoDTO,
        @PathVariable Long id,
        @RequestBody ConcertUpdateDTO dto
    ) {
        concertUpdateService.update(userInfoDTO.getUserId(), id, dto);
        return ResponseEntity.ok().build();
    }
} 