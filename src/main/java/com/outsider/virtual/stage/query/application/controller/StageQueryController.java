package com.outsider.virtual.stage.query.application.controller;

import com.outsider.virtual.stage.query.application.dto.StageDTO;
import com.outsider.virtual.stage.query.application.service.StageQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stages")
@Tag(name = "무대 조회", description = "무대 조회 API")
public class StageQueryController {

    private final StageQueryService stageQueryService;

    public StageQueryController(StageQueryService stageQueryService) {
        this.stageQueryService = stageQueryService;
    }

    @GetMapping
    @Operation(summary = "모든 스테이지 조회", description = "모든 스테이지 목록을 페이징 형태로 조회합니다.")
    public ResponseEntity<Page<StageDTO>> getAllStages(Pageable pageable) {
        Page<StageDTO> stages = stageQueryService.getAllStages(pageable);
        return ResponseEntity.ok(stages);
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID로 스테이지 조회", description = "스테이지 ID를 이용하여 해당 스테이지의 세부 정보를 조회합니다.")
    public ResponseEntity<StageDTO> getStageById(@PathVariable Long id) {
        StageDTO stageDTO = stageQueryService.getStageById(id);
        return ResponseEntity.ok(stageDTO);
    }
}
