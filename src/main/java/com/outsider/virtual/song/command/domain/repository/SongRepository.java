package com.outsider.virtual.song.command.domain.repository;

import com.outsider.virtual.song.command.domain.aggregate.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByTitleAndArtistId(String title, Long artistId);
    Optional<Song> findByTitleAndArtistIdAndConcertId(String title, Long artistId, Long concertId);
    @Transactional
    void deleteByConcertId(Long concertId);
} 