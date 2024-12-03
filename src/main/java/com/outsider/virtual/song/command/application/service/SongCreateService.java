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
        Song song = songCreateMapper.toEntity(dto);
        songRepository.save(song);
        return song.getId();
    }
} 