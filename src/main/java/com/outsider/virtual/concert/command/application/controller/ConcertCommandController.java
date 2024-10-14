package com.outsider.virtual.concert.command.application.controller;

import com.outsider.virtual.concert.command.application.service.ConcertCreateService;
import com.outsider.virtual.concert.command.application.service.ConcertUpdateService;
import com.outsider.virtual.concert.command.application.service.ConcertDeleteService;
import com.outsider.virtual.concert.command.application.dto.ConcertCreateDTO;
import com.outsider.virtual.concert.command.application.dto.ConcertUpdateDTO;
import com.outsider.virtual.concert.command.application.dto.ConcertDeleteDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Hidden
@RestController
@RequestMapping("/api/v1/concerts")
public class ConcertCommandController {

    private final ConcertCreateService concertCreateService;
    private final ConcertUpdateService concertUpdateService;
    private final ConcertDeleteService concertDeleteService;

    public ConcertCommandController(
            ConcertCreateService concertCreateService,
            ConcertUpdateService concertUpdateService,
            ConcertDeleteService concertDeleteService) {
        this.concertCreateService = concertCreateService;
        this.concertUpdateService = concertUpdateService;
        this.concertDeleteService = concertDeleteService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody ConcertCreateDTO dto) {
        concertCreateService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("성공적으로 생성되었습니다.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Long id,
            @RequestBody ConcertUpdateDTO dto) {
        dto.setId(id);
         concertUpdateService.update(dto);
        return ResponseEntity.ok("성공적으로 업데이트되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ConcertDeleteDTO dto = new ConcertDeleteDTO();
        dto.setId(id);
        concertDeleteService.delete(dto);
        return ResponseEntity.noContent().build();
    }
}
