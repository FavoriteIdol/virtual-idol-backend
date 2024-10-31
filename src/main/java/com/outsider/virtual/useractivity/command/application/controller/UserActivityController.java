package com.outsider.virtual.useractivity.command.application.controller;

import com.outsider.virtual.useractivity.command.application.service.ConcertCollectionService;
import com.outsider.virtual.useractivity.command.application.service.ViewHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-activity")
@RequiredArgsConstructor
public class UserActivityController {

    private final ViewHistoryService viewHistoryService;
    private final ConcertCollectionService concertCollectionService;


    @PostMapping("/view-history")
    public ResponseEntity<Long> createViewHistory(@RequestParam Long userId, @RequestParam Long concertId) {
        Long historyId = viewHistoryService.createViewHistory(userId, concertId);
        return ResponseEntity.ok(historyId);
    }

    @PostMapping("/collect-concert")
    public ResponseEntity<Long> collectConcert(@RequestParam Long userId, @RequestParam Long concertId) {
        Long collectionId = concertCollectionService.collectConcert(userId, concertId);
        return ResponseEntity.ok(collectionId);
    }
}
