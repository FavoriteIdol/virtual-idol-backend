package com.outsider.virtual.concert.command.domain.repository;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
