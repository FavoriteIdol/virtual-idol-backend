package com.outsider.virtual.useractivity.query.controller;


import com.outsider.virtual.useractivity.query.service.ConcertCollectionQueryService;
import com.outsider.virtual.useractivity.query.dto.CollectionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/collections")
@RequiredArgsConstructor
public class ConcertCollectionQueryController {

    private final ConcertCollectionQueryService concertCollectionService;



    @GetMapping("/user/{userId}")
    public Page<CollectionDTO> getUserCollections(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return concertCollectionService.getUserCollections(userId, pageable);
    }
}
