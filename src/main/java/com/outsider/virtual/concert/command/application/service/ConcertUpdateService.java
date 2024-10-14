package com.outsider.virtual.concert.command.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.concert.command.application.dto.ConcertUpdateDTO;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ConcertUpdateService {

    private final ConcertRepository concertRepository;

    public ConcertUpdateService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public void update(ConcertUpdateDTO dto) {
        Optional<Concert> optionalEntity = concertRepository.findById(dto.getId());
        if (optionalEntity.isPresent()) {
            Concert entity = optionalEntity.get();
            entity.setName(dto.getName());
            concertRepository.save(entity);
        } else {
            // 예외 처리 (엔티티를 찾을 수 없는 경우)
            throw new RuntimeException("Concert not found");
        }
    }



    // DTO -> Entity 변환 (필요 시)
    public Concert convertToEntity(ConcertUpdateDTO dto) {
        Concert entity = new Concert();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
