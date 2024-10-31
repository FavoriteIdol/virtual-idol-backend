package com.outsider.virtual.concert.command.application.service;

import com.outsider.virtual.concert.command.application.mapper.ConcertCreateMapper;
import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.concert.command.application.dto.ConcertCreateDTO;
import com.outsider.virtual.stage.command.domain.repository.StageRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ConcertCreateService {

    private final ConcertRepository concertRepository;
    private final ConcertCreateMapper concertCreateMapper;
    private final StageRepository stageRepository;

    @Autowired
    public ConcertCreateService(ConcertRepository concertRepository,
                                ConcertCreateMapper concertCreateMapper,
                                StageRepository stageRepository) {
        this.concertRepository = concertRepository;
        this.concertCreateMapper = concertCreateMapper;
        this.stageRepository = stageRepository;
    }

    public Long register(Long userId, ConcertCreateDTO dto) {
        // Stage ID가 존재하는지 확인
        if (!stageRepository.existsById(dto.getStageId())) {
            throw new IllegalArgumentException("Stage ID " + dto.getStageId() + " does not exist.");
        }

        Concert entity = new Concert();
        concertCreateMapper.toEntity(dto, userId, entity);
        concertRepository.save(entity);
        return entity.getId();
    }
}
