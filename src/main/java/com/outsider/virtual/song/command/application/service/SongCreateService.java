package com.outsider.virtual.song.command.application.service;

import com.outsider.virtual.song.command.application.dto.SongCreateDTO;
import com.outsider.virtual.song.command.application.mapper.SongCreateMapper;
import com.outsider.virtual.song.command.domain.aggregate.Song;
import com.outsider.virtual.song.command.domain.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SongCreateService {

    private final SongRepository songRepository;
    private final SongCreateMapper songCreateMapper;

    public SongCreateService(SongRepository songRepository, SongCreateMapper songCreateMapper) {
        this.songRepository = songRepository;
        this.songCreateMapper = songCreateMapper;
    }

    @Transactional
    public Long register(SongCreateDTO dto) {
        songRepository.findByTitleAndArtistIdAndConcertId(
            dto.getTitle(), 
            dto.getArtistId(), 
            dto.getConcertId()
        ).ifPresent(s -> {
            throw new IllegalStateException("이미 해당 콘서트에 존재하는 노래입니다.");
        });

        Song song = songCreateMapper.toEntity(dto);
        song = songRepository.save(song);
        return song.getId();
    }
} 