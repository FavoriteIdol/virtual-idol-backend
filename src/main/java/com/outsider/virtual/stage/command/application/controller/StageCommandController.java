package com.outsider.virtual.stage.command.application.controller;

import com.outsider.virtual.stage.command.application.service.StageCreateService;
import com.outsider.virtual.stage.command.application.service.StageUpdateService;
import com.outsider.virtual.stage.command.application.service.StageDeleteService;
import com.outsider.virtual.stage.command.application.dto.StageCreateDTO;
import com.outsider.virtual.stage.command.application.dto.StageUpdateDTO;
import com.outsider.virtual.stage.command.application.dto.StageDeleteDTO;
import com.outsider.virtual.user.command.infrastructure.service.CustomUserDetail;
import com.outsider.virtual.user.dto.CustomUserInfoDTO;
import com.outsider.virtual.util.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stages")
@Tag(name = "무대 관리", description = "무대 생성, 수정, 삭제를 위한 API")
public class StageCommandController {

    private final StageCreateService stageCreateService;
    private final StageUpdateService stageUpdateService;
    private final StageDeleteService stageDeleteService;

    public StageCommandController(
            StageCreateService stageCreateService,
            StageUpdateService stageUpdateService,
            StageDeleteService stageDeleteService) {
        this.stageCreateService = stageCreateService;
        this.stageUpdateService = stageUpdateService;
        this.stageDeleteService = stageDeleteService;
    }

    @Operation(summary = "새로운 무대 생성", description = "제공된 세부 정보를 사용하여 새로운 무대를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "무대가 성공적으로 생성되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력 데이터가 잘못되었습니다.")
    })
    @PostMapping
    public ResponseEntity<String> register(@UserId CustomUserInfoDTO userId, @RequestBody StageCreateDTO dto) {
        if(userId!=null)
        {
            stageCreateService.register(userId.getUserId(), dto);

        }else
        {
            throw new RuntimeException("인증 이후 가능합니다.");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("무대가 성공적으로 생성되었습니다."); // 성공 메시지를 포함한 응답
    }

    @Operation(summary = "기존 무대 수정", description = "지정된 ID의 기존 무대 정보를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "무대가 성공적으로 업데이트되었습니다."),
            @ApiResponse(responseCode = "404", description = "무대를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "입력 데이터가 잘못되었습니다.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Long id,
            @RequestBody StageUpdateDTO dto) {
        stageUpdateService.update(id,dto);
        return ResponseEntity.ok("무대가 성공적으로 업데이트되었습니다."); // 성공 메시지를 포함한 응답
    }

    @Operation(summary = "무대 삭제", description = "ID를 통해 기존 무대를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "무대가 성공적으로 삭제되었습니다."),
            @ApiResponse(responseCode = "404", description = "무대를 찾을 수 없습니다.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stageDeleteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
