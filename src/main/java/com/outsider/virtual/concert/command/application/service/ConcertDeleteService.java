package com.outsider.virtual.concert.command.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.concert.command.application.dto.ConcertDeleteDTO;
import com.outsider.virtual.user.exception.NotExistException;
import com.outsider.virtual.user.exception.NotMineException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConcertDeleteService {

    private final ConcertRepository concertRepository;

    public ConcertDeleteService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public void delete(Long userId,Long id) {
    Concert concert =  concertRepository.findById(id).orElseThrow(NotExistException::new);
        if(!concert.getUserId().equals(userId))
        {
            throw new NotMineException();
        }
        concertRepository.deleteById(id);
    }
}
