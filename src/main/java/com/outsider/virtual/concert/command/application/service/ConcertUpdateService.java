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
        if (dto.getSongIds() != null) {
            // 기존 ConcertSong 관계 모두 제거
            concertSongRepository.deleteAllByConcertId(id);
            
            // 새로운 Song 관계 추가
            List<Song> songs = songRepository.findAllById(dto.getSongIds());
            if (songs.size() != dto.getSongIds().size()) {
                throw new IllegalArgumentException("일부 Song이 존재하지 않습니다.");
            }
            
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
