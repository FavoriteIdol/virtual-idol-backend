package com.outsider.virtual.stage.command.application.service;

import com.outsider.virtual.stage.command.application.mapper.StageCreateMapper;
import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import com.outsider.virtual.stage.command.domain.repository.StageRepository;
import com.outsider.virtual.stage.command.application.dto.StageCreateDTO;
import org.springframework.stereotype.Service;

@Service
public class StageCreateService {

    private final StageRepository stageRepository;
    private final StageCreateMapper stageCreateMapper; // MapStruct 매퍼

    public StageCreateService(StageRepository stageRepository, StageCreateMapper stageCreateMapper) {
        this.stageRepository = stageRepository;
        this.stageCreateMapper = stageCreateMapper;
    }

    public void register(Long userId, StageCreateDTO dto) {
        Stage entity = stageCreateMapper.toEntity(dto, userId); // MapStruct 매퍼 사용
        stageRepository.save(entity);
    }
}