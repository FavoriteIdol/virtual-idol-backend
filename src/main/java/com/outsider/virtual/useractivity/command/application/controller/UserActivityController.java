package com.outsider.virtual.useractivity.command.application.controller;

import com.outsider.virtual.user.dto.CustomUserInfoDTO;
import com.outsider.virtual.useractivity.command.application.service.ConcertCollectionService;
import com.outsider.virtual.useractivity.command.application.service.ViewHistoryService;
import com.outsider.virtual.util.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "사용자 활동", description = "사용자 활동 관리 API")
@RestController
@RequestMapping("/api/user-activity")
@RequiredArgsConstructor
public class UserActivityController {

    private final ViewHistoryService viewHistoryService;
    private final ConcertCollectionService concertCollectionService;

    @Operation(summary = "조회 기록 생성", description = "사용자가 콘서트를 조회한 기록을 생성합니다.")
    @PostMapping("/view-history")
    public ResponseEntity<Long> createViewHistory(
            @UserId CustomUserInfoDTO userId,
            @RequestBody Long concertId) {
        Long historyId = viewHistoryService.createViewHistory(userId.getUserId(), concertId);
        return ResponseEntity.ok(historyId);
    }

    @Operation(summary = "콘서트 수집", description = "사용자가 콘서트를 수집 목록에 추가합니다.")
    @PostMapping("/collect-concert")
    public ResponseEntity<Long> collectConcert(
            @UserId CustomUserInfoDTO userId,
            @RequestBody Long concertId) {
        Long collectionId = concertCollectionService.collectConcert(userId.getUserId(), concertId);
        return ResponseEntity.ok(collectionId);
    }
}
