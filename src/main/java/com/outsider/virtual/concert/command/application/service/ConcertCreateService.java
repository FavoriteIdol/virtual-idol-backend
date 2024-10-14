package com.outsider.virtual.concert.command.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.concert.command.application.dto.ConcertCreateDTO;
import org.springframework.stereotype.Service;

@Service
public class ConcertCreateService {

    private final ConcertRepository concertRepository;

    public ConcertCreateService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public void register(ConcertCreateDTO dto) {
        Concert entity = convertToEntity(dto);
        concertRepository.save(entity);
    }


    // DTO -> Entity 변환
    public Concert convertToEntity(ConcertCreateDTO dto) {
        return new Concert(dto.getName());
    }
}
