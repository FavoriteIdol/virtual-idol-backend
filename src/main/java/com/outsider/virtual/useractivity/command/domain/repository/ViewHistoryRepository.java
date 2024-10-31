package com.outsider.virtual.useractivity.command.domain.repository;


import com.outsider.virtual.useractivity.command.domain.aggregate.ViewHistory;
import com.outsider.virtual.useractivity.query.dto.ViewHistoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {
    List<ViewHistory> findByUserId(Long userId);
    List<ViewHistory> findByUserIdAndConcertId(Long userId, Long concertId);
    @Query("SELECT new com.outsider.virtual.useractivity.query.dto.ViewHistoryDTO(" +
            "v.concert.id, v.concert.name, v.concert.img, v.viewDate) " +
            "FROM ViewHistory v WHERE v.user.id = :userId")
    Page<ViewHistoryDTO> findByUserId(@Param("userId") Long userId, Pageable pageable);
}

