package com.outsider.virtual.character.command.application.controller;

import com.outsider.virtual.character.command.application.dto.CharacterCreateDTO;
import com.outsider.virtual.character.command.application.dto.CharacterUpdateDTO;
import com.outsider.virtual.character.command.application.service.CharacterCommandService;
import com.outsider.virtual.character.query.application.dto.CharacterDTO;
import com.outsider.virtual.character.query.application.service.CharacterQueryService;
import com.outsider.virtual.user.dto.CustomUserInfoDTO;
import com.outsider.virtual.util.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
@Tag(name = "캐릭터 관리", description = "캐릭터 CRUD API")
public class CharacterController {

    private final CharacterCommandService characterCommandService;
    private final CharacterQueryService characterQueryService;

    public CharacterController(CharacterCommandService characterCommandService,
                             CharacterQueryService characterQueryService) {
        this.characterCommandService = characterCommandService;
        this.characterQueryService = characterQueryService;
    }

    @Operation(summary = "캐릭터 생성", description = "새로운 캐릭터를 생성합니다.")
    @PostMapping
    public ResponseEntity<Long> create(
            @Parameter(hidden = true) @UserId CustomUserInfoDTO userInfoDTO,
            @ModelAttribute CharacterCreateDTO dto) throws Exception {
        Long characterId = characterCommandService.create(userInfoDTO.getUserId(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterId);
    }

    @Operation(summary = "캐릭터 수정", description = "기존 캐릭터를 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @Parameter(hidden = true) @UserId CustomUserInfoDTO userInfoDTO,
            @PathVariable Long id,
            @ModelAttribute CharacterUpdateDTO dto) throws Exception {
        characterCommandService.update(userInfoDTO.getUserId(), id, dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "캐릭터 삭제", description = "캐릭터를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(hidden = true) @UserId CustomUserInfoDTO userInfoDTO,
            @PathVariable Long id) {
        characterCommandService.delete(userInfoDTO.getUserId(), id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "사용자의 캐릭터 목록 조회", description = "특정 사용자의 모든 캐릭터를 조회합니다.")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CharacterDTO>> getCharactersByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(characterQueryService.getCharactersByUserId(userId));
    }

    @Operation(summary = "캐릭터 보유 여부 확인", description = "사용자의 캐릭터 보유 여부를 확인합니다.")
    @GetMapping("/check/{userId}")
    public ResponseEntity<Boolean> hasCharacter(@PathVariable Long userId) {
        return ResponseEntity.ok(characterQueryService.hasCharacter(userId));
    }
} 