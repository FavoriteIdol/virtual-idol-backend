package com.outsider.virtual.song.command.domain.repository;

import com.outsider.virtual.song.command.domain.aggregate.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
} 