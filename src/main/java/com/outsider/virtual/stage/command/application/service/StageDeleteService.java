package com.outsider.virtual.stage.command.application.service;

import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import com.outsider.virtual.stage.command.domain.repository.StageRepository;
import com.outsider.virtual.stage.command.application.dto.StageDeleteDTO;
import com.outsider.virtual.user.exception.NotExistException;
import com.outsider.virtual.user.exception.NotMineException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StageDeleteService {

    private final StageRepository stageRepository;

    public StageDeleteService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public void delete(Long userId,Long id) {
       Stage entity=  stageRepository.findById(id).orElseThrow(NotExistException::new);

       if(!entity.getUserId().equals(userId))
        {
            throw new NotMineException();
        }
        stageRepository.deleteById(id);


    }
}
