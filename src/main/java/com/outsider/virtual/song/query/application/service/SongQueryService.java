package com.outsider.virtual.song.query.application.service;

import com.outsider.virtual.song.query.application.dto.SongDTO;
import com.outsider.virtual.song.query.domain.repository.SongQueryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SongQueryService {

    private final SongQueryRepository songQueryRepository;

    public SongQueryService(SongQueryRepository songQueryRepository) {
        this.songQueryRepository = songQueryRepository;
    }

    public List<SongDTO> getSongsByUserId(Long userId) {
        return songQueryRepository.findSongsByUserId(userId);
    }
} 