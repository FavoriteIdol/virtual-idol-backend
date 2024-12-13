package com.outsider.virtual.concert.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.outsider.virtual.concert.command.domain.aggregate.ConcertSong;

public interface ConcertSongRepository extends JpaRepository<ConcertSong, Long> {
    void deleteAllByConcertId(Long concertId);
    void deleteBySongId(Long songId);
} 