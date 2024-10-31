package com.outsider.virtual.useractivity.query.controller;

import com.outsider.virtual.useractivity.query.dto.ViewHistoryDTO;
import com.outsider.virtual.useractivity.query.service.ViewHistoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/view-history")
@RequiredArgsConstructor
public class ViewHistoryQueryController {

    private final ViewHistoryQueryService viewHistoryQueryService;

    @GetMapping("/user/{userId}")
    public Page<ViewHistoryDTO> getUserViewHistory(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return viewHistoryQueryService.getUserViewHistory(userId, pageable);
    }
}
