package com.outsider.virtual.concert.query.application.controller;

import com.outsider.virtual.concert.query.application.dto.ConcertInfoDTO;
import com.outsider.virtual.concert.query.application.dto.PerformanceDTO;
import com.outsider.virtual.concert.query.application.service.ConcertQueryService;
import com.outsider.virtual.concert.query.application.dto.ConcertDTO;
import com.outsider.virtual.stage.query.application.dto.StageDTO;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/concerts")
@Tag(name="공연장 조회", description = "공연장 조회를 위한 API ")
public class ConcertQueryController {

    private final ConcertQueryService concertQueryService;

    public ConcertQueryController(ConcertQueryService concertQueryService) {
        this.concertQueryService = concertQueryService;
    }
    @Operation(summary = "모든 콘서트 조회", description = "모든 콘서트 정보를 페이지네이션된 형태로 조회합니다.")
    @GetMapping
    public ResponseEntity<Page<ConcertDTO>> getAllConcerts(@PageableDefault(size = 10, page = 0,sort="concertDate") Pageable pageable) {
        return ResponseEntity.ok(concertQueryService.getAllConcerts(pageable));
    }
    @Operation(summary = "특정 콘서트 조회", description = "지정된 ID에 해당하는 콘서트 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ConcertInfoDTO getConcertById(@PathVariable Long id) {
        return concertQueryService.getConcertById(id);
    }
    @Operation(summary = "특정 사용자의 콘서트 조회", description = "특정 버튜버의 콘서트를 페이지네이션된 형태로 조회합니다.")
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ConcertDTO>> getConcertsByUserId(
            @PathVariable Long userId,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return ResponseEntity.ok(concertQueryService.getConcertsByUserId(userId, pageable));
    }


    @GetMapping("/by-month")
    public ResponseEntity<List<PerformanceDTO>> getConcertsByYearAndMonth(
            @RequestParam int year,
            @RequestParam int month) {
        List<PerformanceDTO> concerts = concertQueryService.getConcertsByYearAndMonth(year, month);
        return ResponseEntity.ok(concerts);
    }
    @GetMapping("/imminent")
    public List<PerformanceDTO> getImminentConcerts(@RequestParam(defaultValue = "5") int limit) {
        return concertQueryService.getImminentConcerts(limit);
    }
}
