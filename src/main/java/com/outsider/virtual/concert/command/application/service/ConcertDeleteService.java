package com.outsider.virtual.concert.command.application.service;

import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.concert.command.application.dto.ConcertDeleteDTO;
import org.springframework.stereotype.Service;

@Service
public class ConcertDeleteService {

    private final ConcertRepository concertRepository;

    public ConcertDeleteService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public void delete(ConcertDeleteDTO dto) {
        concertRepository.deleteById(dto.getId());
    }
}
