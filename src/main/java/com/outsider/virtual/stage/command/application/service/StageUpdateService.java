package com.outsider.virtual.stage.command.application.service;

import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import com.outsider.virtual.stage.command.domain.repository.StageRepository;
import com.outsider.virtual.stage.command.application.dto.StageUpdateDTO;
import com.outsider.virtual.stage.command.application.mapper.StageUpdateMapper;
import org.springframework.stereotype.Service;

@Service
public class StageUpdateService {

    private final StageRepository stageRepository;
    private final StageUpdateMapper stageUpdateMapper; // MapStruct 매퍼 추가

    public StageUpdateService(StageRepository stageRepository, StageUpdateMapper stageUpdateMapper) {
        this.stageRepository = stageRepository;
        this.stageUpdateMapper = stageUpdateMapper;
    }

    public void update(Long id,StageUpdateDTO dto) {
        Stage entity = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage not found"));

        // MapStruct 매퍼를 사용하여 엔티티 필드 업데이트
        stageUpdateMapper.updateStageFromDto(dto, entity);
        stageRepository.save(entity);
    }
}
