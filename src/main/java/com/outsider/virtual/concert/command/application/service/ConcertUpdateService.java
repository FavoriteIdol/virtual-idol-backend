package com.outsider.virtual.concert.command.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.concert.command.application.dto.ConcertUpdateDTO;
import com.outsider.virtual.user.exception.NotExistException;
import com.outsider.virtual.user.exception.NotMineException;
import com.outsider.virtual.concert.command.domain.aggregate.ConcertSong;
import com.outsider.virtual.concert.command.domain.repository.ConcertSongRepository;
import com.outsider.virtual.song.command.domain.aggregate.Song;
import com.outsider.virtual.song.command.domain.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConcertUpdateService {

    private final ConcertRepository concertRepository;
    private final SongRepository songRepository;
    private final ConcertSongRepository concertSongRepository;

    public ConcertUpdateService(
            ConcertRepository concertRepository, 
            SongRepository songRepository,
            ConcertSongRepository concertSongRepository) {
        this.concertRepository = concertRepository;
        this.songRepository = songRepository;
        this.concertSongRepository = concertSongRepository;
    }

    public void update(Long userId, Long id, ConcertUpdateDTO dto) {
        Concert concert = concertRepository.findById(id)
            .orElseThrow(() -> new NotExistException());

        if (!concert.getUserId().equals(userId)) {
            throw new NotMineException();
        }

        // 값이 있는 필드만 업데이트
        if (StringUtils.hasText(dto.getName())) {
            concert.setName(dto.getName());
        }
        if (dto.getConcertDate() != null) {
            concert.setConcertDate(dto.getConcertDate());
        }
        if (dto.getStartTime() != null) {
            concert.setStartTime(dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            concert.setEndTime(dto.getEndTime());
        }
        if (StringUtils.hasText(dto.getTicketPrice())) {
            concert.setTicketPrice(dto.getTicketPrice());
        }
        if (StringUtils.hasText(dto.getPeopleScale())) {
            concert.setPeopleScale(dto.getPeopleScale());
        }
        if (dto.getSongIds() != null && !dto.getSongIds().isEmpty()) {
            // null 값 필터링
            List<Long> validSongIds = dto.getSongIds().stream()
                .filter(songId -> songId != null)
                .collect(Collectors.toList());

            if (validSongIds.isEmpty()) {
                return;  // 유효한 songId가 없으면 처리하지 않음
            }

            // 기존 ConcertSong 관계 모두 제거
            concertSongRepository.deleteAllByConcertId(id);
            
            // 존재하지 않는 Song ID 찾기
            List<Song> songs = songRepository.findAllById(validSongIds);
            Set<Long> foundSongIds = songs.stream()
                .map(Song::getId)
                .collect(Collectors.toSet());
            
            List<Long> notFoundSongIds = validSongIds.stream()
                .filter(songId -> !foundSongIds.contains(songId))
                .collect(Collectors.toList());
            
            if (!notFoundSongIds.isEmpty()) {
                throw new IllegalArgumentException(
                    String.format("다음 ID를 가진 노래들이 존재하지 않습니다: %s", notFoundSongIds)
                );
            }
            
            // 새로운 ConcertSong 관계 생성
            songs.forEach(song -> {
                ConcertSong concertSong = new ConcertSong(concert, song);
                concertSongRepository.save(concertSong);
            });
        }
        if (StringUtils.hasText(dto.getImg())) {
            concert.setImg(dto.getImg());
        }

        concertRepository.save(concert);
    }
}
