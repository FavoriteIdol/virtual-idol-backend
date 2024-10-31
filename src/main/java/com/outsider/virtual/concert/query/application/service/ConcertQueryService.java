package com.outsider.virtual.concert.query.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.query.application.dto.ConcertInfoDTO;
import com.outsider.virtual.concert.query.application.dto.PerformanceDTO;
import com.outsider.virtual.concert.query.domain.repository.ConcertInfoMapper;
import com.outsider.virtual.concert.query.domain.repository.ConcertMapper;
import com.outsider.virtual.concert.query.domain.repository.ConcertQueryRepository;
import com.outsider.virtual.concert.query.application.dto.ConcertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertQueryService {

    private final ConcertQueryRepository concertQueryRepository;
    private final ConcertInfoMapper concertMapper;
    public ConcertQueryService(ConcertQueryRepository concertQueryRepository, ConcertInfoMapper concertMapper) {
        this.concertQueryRepository = concertQueryRepository;
        this.concertMapper = concertMapper;
    }

    public Page<ConcertDTO> getAllConcerts(Pageable pageable) {
        return concertQueryRepository.findAllConcertWithUsers(pageable);
    }

    public ConcertInfoDTO getConcertById(Long id) {
        Concert entity = concertQueryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concert not found"));
        return concertMapper.toDTO(entity);
    }
    public Page<ConcertDTO> getConcertsByUserId(Long userId, Pageable pageable) {
        if (userId == null) {
            throw new IllegalArgumentException("userId는 null일 수 없습니다.");
        }
        return concertQueryRepository.findConcertsByUserId(userId, pageable);
    }
    // Entity -> DTO 변환
    public List<PerformanceDTO> getConcertsByYearAndMonth(int year, int month) {
        return concertQueryRepository.findConcertsByYearAndMonth(year, month);
    }


    public List<PerformanceDTO> getImminentConcerts(int limit) {
        return concertQueryRepository.findImminentConcerts(limit);
    }
}
