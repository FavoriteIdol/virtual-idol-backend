package com.outsider.virtual.stage.query.application.service;

import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import com.outsider.virtual.stage.query.application.dto.StageDTO;
import com.outsider.virtual.stage.query.domain.repository.StageQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StageQueryService {

    private final StageQueryRepository stageQueryRepository;

    public StageQueryService(StageQueryRepository stageQueryRepository) {
        this.stageQueryRepository = stageQueryRepository;
    }

    public Page<StageDTO> getAllStages(Pageable pageable) {
        return stageQueryRepository.findAllStagesWithUsers(pageable);
    }

    public StageDTO getStageById(Long id) {
        Stage entity = stageQueryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage not found"));
        return convertToDTO(entity);
    }
    public Page<StageDTO> getStagesByUserId(Long userId, Pageable pageable) {
        if (userId == null) {
            throw new IllegalArgumentException("userId는 null일 수 없습니다.");
        }
        return stageQueryRepository.findStagesByUserId(userId, pageable);
    }
    // Entity -> DTO 변환
    public StageDTO convertToDTO(Stage entity) {
        StageDTO dto = new StageDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTerrain(entity.getTerrain() );
        dto.setSky(entity.getSky());
        dto.setTheme(entity.getTheme() );
        dto.setSpecialEffect(entity.getSpecialEffect() );
        return dto;
    }
}
