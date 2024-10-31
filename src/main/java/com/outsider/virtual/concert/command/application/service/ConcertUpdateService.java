package com.outsider.virtual.concert.command.application.service;

import com.outsider.virtual.concert.command.application.mapper.ConcertUpdateMapper;
import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.concert.command.application.dto.ConcertUpdateDTO;
import com.outsider.virtual.user.exception.NotExistException;
import com.outsider.virtual.user.exception.NotMineException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ConcertUpdateService {

    private final ConcertRepository concertRepository;
    private final ConcertUpdateMapper concertUpdateMapper;
    public ConcertUpdateService(ConcertRepository concertRepository, ConcertUpdateMapper concertUpdateMapper) {
        this.concertRepository = concertRepository;
        this.concertUpdateMapper = concertUpdateMapper;
    }

    public void update(Long userId,Long id , ConcertUpdateDTO dto) {
        Concert entity = concertRepository.findById(id).orElseThrow(NotExistException::new);
        if(!entity.getUserId().equals(userId))
        {
            throw new NotMineException();
        }
        concertUpdateMapper.toEntity(dto,entity);
        concertRepository.save(entity);
    }
}
