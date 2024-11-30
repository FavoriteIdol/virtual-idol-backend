package com.outsider.virtual.useractivity.query.controller;

import com.outsider.virtual.useractivity.query.service.ConcertCollectionQueryService;
import com.outsider.virtual.useractivity.query.dto.CollectionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "콘서트 콜렉션", description = "사용자의 콘서트 수집 정보 조회 API")
@RestController
@RequestMapping("/api/v1/collections")
@RequiredArgsConstructor
public class ConcertCollectionQueryController {

    private final ConcertCollectionQueryService concertCollectionService;

    @Operation(summary = "사용자 수집 목록 조회", description = "사용자가 수집한 콘서트 목록을 페이징하여 조회합니다.")
    @GetMapping("/user/{userId}")
    public Page<CollectionDTO> getUserCollections(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return concertCollectionService.getUserCollections(userId, pageable);
    }
}
