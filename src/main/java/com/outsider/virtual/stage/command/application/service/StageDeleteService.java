package com.outsider.virtual.stage.command.application.service;

import com.outsider.virtual.stage.command.domain.repository.StageRepository;
import com.outsider.virtual.stage.command.application.dto.StageDeleteDTO;
import org.springframework.stereotype.Service;

@Service
public class StageDeleteService {

    private final StageRepository stageRepository;

    public StageDeleteService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public void delete(Long id) {
        stageRepository.deleteById(id);
    }
}
