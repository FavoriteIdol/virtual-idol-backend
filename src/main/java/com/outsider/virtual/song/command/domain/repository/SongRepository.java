package com.outsider.virtual.song.command.domain.repository;

import com.outsider.virtual.song.command.domain.aggregate.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByTitleAndArtistId(String title, Long artistId);
} 