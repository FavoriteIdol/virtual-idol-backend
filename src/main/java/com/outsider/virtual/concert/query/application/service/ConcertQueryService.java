package com.outsider.virtual.concert.query.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.query.domain.repository.ConcertQueryRepository;
import com.outsider.virtual.concert.query.application.dto.ConcertDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertQueryService {

    private final ConcertQueryRepository concertQueryRepository;

    public ConcertQueryService(ConcertQueryRepository concertQueryRepository) {
        this.concertQueryRepository = concertQueryRepository;
    }

    public List<ConcertDTO> getAllConcerts() {
        List<Concert> entities = concertQueryRepository.findAll();
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ConcertDTO getConcertById(Long id) {
        Concert entity = concertQueryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concert not found"));
        return convertToDTO(entity);
    }

    // Entity -> DTO 변환
    public ConcertDTO convertToDTO(Concert entity) {
        ConcertDTO dto = new ConcertDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
