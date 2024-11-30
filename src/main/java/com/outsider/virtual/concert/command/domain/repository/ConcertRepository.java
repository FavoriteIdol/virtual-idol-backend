package com.outsider.virtual.concert.command.domain.repository;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
    // Stage 엔티티의 name 필드로 Stage 찾기
    Optional<Concert> findByName(String name);
}
