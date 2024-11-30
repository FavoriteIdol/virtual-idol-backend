package com.outsider.virtual.useractivity.query.controller;

import com.outsider.virtual.useractivity.query.dto.ViewHistoryDTO;
import com.outsider.virtual.useractivity.query.service.ViewHistoryQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "관람 이력", description = "사용자의 조회 기록 정보 조회 API")
@RestController
@RequestMapping("/api/v1/view-history")
@RequiredArgsConstructor
public class ViewHistoryQueryController {

    private final ViewHistoryQueryService viewHistoryQueryService;

    @Operation(summary = "사용자 조회 기록 조회", description = "사용자가 조회한 콘서트의 기록을 페이징하여 조회합니다.")
    @GetMapping("/user/{userId}")
    public Page<ViewHistoryDTO> getUserViewHistory(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return viewHistoryQueryService.getUserViewHistory(userId, pageable);
    }
}
