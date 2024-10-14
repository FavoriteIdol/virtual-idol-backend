package com.outsider.virtual.concert.query.application.controller;

import com.outsider.virtual.concert.query.application.service.ConcertQueryService;
import com.outsider.virtual.concert.query.application.dto.ConcertDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Hidden
@RestController
@RequestMapping("/api/v1/concerts")
public class ConcertQueryController {

    private final ConcertQueryService concertQueryService;

    public ConcertQueryController(ConcertQueryService concertQueryService) {
        this.concertQueryService = concertQueryService;
    }

    @GetMapping
    public List<ConcertDTO> getAllConcerts() {
        return concertQueryService.getAllConcerts();
    }

    @GetMapping("/{id}")
    public ConcertDTO getConcertById(@PathVariable Long id) {
        return concertQueryService.getConcertById(id);
    }
}
