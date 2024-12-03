package com.outsider.virtual.concert.query.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.query.application.dto.ConcertInfoDTO;
import com.outsider.virtual.concert.query.application.dto.PerformanceDTO;
import com.outsider.virtual.concert.query.domain.repository.ConcertInfoMapper;
import com.outsider.virtual.concert.query.domain.repository.ConcertMapper;
import com.outsider.virtual.concert.query.domain.repository.ConcertQueryRepository;
import com.outsider.virtual.song.command.domain.aggregate.Song;
import com.outsider.virtual.song.command.domain.repository.SongRepository;
import com.outsider.virtual.song.query.application.dto.SongDTO;
import com.outsider.virtual.song.query.application.mapper.SongMapper;
import com.outsider.virtual.concert.query.application.dto.ConcertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertQueryService {

    private final ConcertQueryRepository concertQueryRepository;
    private final ConcertInfoMapper concertMapper;
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public ConcertQueryService(
            ConcertQueryRepository concertQueryRepository, 
            ConcertInfoMapper concertMapper,
            SongRepository songRepository,
            SongMapper songMapper) {
        this.concertQueryRepository = concertQueryRepository;
        this.concertMapper = concertMapper;
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    public Page<ConcertDTO> getAllConcerts(Pageable pageable) {
        Page<ConcertDTO> concertPage = concertQueryRepository.findAllConcertWithUsers(pageable);
        
        concertPage.getContent().forEach(dto -> {
            Concert concert = concertQueryRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Concert not found"));
                    
            if (concert.getSongIds() != null && !concert.getSongIds().isEmpty()) {
                List<Song> songs = songRepository.findAllById(concert.getSongIds());
                if (!songs.isEmpty()) {
                    dto.setSongs(songs.stream()
                            .map(songMapper::toDTO)
                            .collect(Collectors.toList()));
                }
            }
        });
        
        return concertPage;
    }

    public ConcertInfoDTO getConcertById(Long id) {
        Concert concert = concertQueryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concert not found"));
                
        ConcertInfoDTO dto = concertMapper.toDTO(concert);
        
        if (concert.getSongIds() != null && !concert.getSongIds().isEmpty()) {
            List<Song> songs = songRepository.findAllById(concert.getSongIds());
            if (!songs.isEmpty()) {
                dto.setSongs(songs.stream()
                        .map(songMapper::toDTO)
                        .collect(Collectors.toList()));
            }
        }
        
        return dto;
    }

    public Page<ConcertDTO> getConcertsByUserId(Long userId, Pageable pageable) {
        if (userId == null) {
            throw new IllegalArgumentException("userId는 null일 수 없습니다.");
        }
        return concertQueryRepository.findConcertsByUserId(userId, pageable);
    }
    // Entity -> DTO 변환
    public List<PerformanceDTO> getConcertsByYearAndMonth(int year, int month) {
        return concertQueryRepository.findConcertsByYearAndMonth(year, month);
    }


    public List<PerformanceDTO> getImminentConcerts(int limit) {
        return concertQueryRepository.findImminentConcerts(limit);
    }
}
