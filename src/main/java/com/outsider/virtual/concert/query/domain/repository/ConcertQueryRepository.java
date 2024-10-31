package com.outsider.virtual.concert.query.domain.repository;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertQueryRepository extends JpaRepository<Concert, Long> , ConcertRepositoryCustom {
}
