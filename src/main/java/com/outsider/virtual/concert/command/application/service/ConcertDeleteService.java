package com.outsider.virtual.concert.command.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.song.command.domain.repository.SongRepository;
import com.outsider.virtual.user.exception.NotExistException;
import com.outsider.virtual.user.exception.NotMineException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConcertDeleteService {

    private final ConcertRepository concertRepository;
    private final SongRepository songRepository;

    public ConcertDeleteService(
            ConcertRepository concertRepository,
            SongRepository songRepository) {
        this.concertRepository = concertRepository;
        this.songRepository = songRepository;
    }

    public void delete(Long userId, Long id) {
        Concert concert = concertRepository.findById(id)
                .orElseThrow(NotExistException::new);
                
        if (!concert.getUserId().equals(userId)) {
            throw new NotMineException();
        }

        // 먼저 해당 콘서트에 속한 노래들을 삭제
        songRepository.deleteByConcertId(id);
        
        // 마지막으로 콘서트 삭제
        concertRepository.deleteById(id);
    }
}
