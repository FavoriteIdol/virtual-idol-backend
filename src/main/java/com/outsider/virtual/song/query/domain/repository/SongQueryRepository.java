package com.outsider.virtual.song.query.domain.repository;

import com.outsider.virtual.song.command.domain.aggregate.Song;
import com.outsider.virtual.song.query.application.dto.SongDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongQueryRepository extends JpaRepository<Song, Long> {
    
    @Query("SELECT new com.outsider.virtual.song.query.application.dto.SongDTO(s.id, s.title, s.artistId, s.url) " +
           "FROM Song s " +
           "WHERE s.artistId = :userId")
    List<SongDTO> findSongsByUserId(@Param("userId") Long userId);
} 