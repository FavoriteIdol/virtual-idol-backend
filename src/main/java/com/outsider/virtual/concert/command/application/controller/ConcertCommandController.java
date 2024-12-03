package com.outsider.virtual.concert.command.application.controller;

import com.outsider.virtual.concert.command.application.service.ConcertCreateService;
import com.outsider.virtual.concert.command.application.service.ConcertUpdateService;
import com.outsider.virtual.concert.command.application.service.ConcertDeleteService;
import com.outsider.virtual.concert.command.application.dto.ConcertCreateDTO;
import com.outsider.virtual.concert.command.application.dto.ConcertUpdateDTO;
import com.outsider.virtual.concert.command.application.dto.ConcertDeleteDTO;
import com.outsider.virtual.concert.query.application.dto.CreateResponseDTO;
import com.outsider.virtual.user.dto.CustomUserInfoDTO;
import com.outsider.virtual.user.exception.NotAuthenticationException;
import com.outsider.virtual.util.UserId;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/concerts")
@Tag(name =  "콘서트 관리" , description = " 콘서트 Create ,Update, Delete API")
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
    @Operation(summary = "새로운 콘서트 예약 생성", description = "제공된 세부 정보를 사용하여 새로운 콘서트를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "콘서트가 성공적으로 생성되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력 데이터가 잘못되었습니다.")
    })
    @PostMapping
    public ResponseEntity<CreateResponseDTO> register(@RequestBody ConcertCreateDTO dto, @Parameter(hidden = true) @UserId CustomUserInfoDTO userInfoDTO) {

        Long concertId= concertCreateService.register(userInfoDTO.getUserId(),dto);
        CreateResponseDTO responseDTO = new CreateResponseDTO("성공적으로 생성되었습니다.", concertId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "공연 수정", description = "공연 정보를 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
        @PathVariable Long id,
        @RequestBody ConcertUpdateDTO dto,
        @Parameter(hidden = true) @UserId CustomUserInfoDTO userInfoDTO
    ) {
        concertUpdateService.update(userInfoDTO.getUserId(), id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id ,@Parameter(hidden = true) @UserId CustomUserInfoDTO userInfoDTO) {
        ConcertDeleteDTO dto = new ConcertDeleteDTO();
        dto.setId(id);
        concertDeleteService.delete(userInfoDTO.getUserId(),id);

        return ResponseEntity.noContent().build();
    }
}
