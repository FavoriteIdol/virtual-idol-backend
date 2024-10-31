package com.outsider.virtual.concert.query.domain.repository;

import com.outsider.virtual.concert.query.application.dto.ConcertDTO;
import com.outsider.virtual.concert.query.application.dto.PerformanceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConcertRepositoryCustom {
    Page<ConcertDTO> findAllConcertWithUsers(Pageable pageable);
    Page<ConcertDTO> findConcertsByUserId(Long userId, Pageable pageable); // New method

    List<PerformanceDTO> findConcertsByYearAndMonth(int year, int month);

    // 추가된 메서드: 임박한 공연을 가져오는 메서드
    List<PerformanceDTO> findImminentConcerts(int limit);
}
