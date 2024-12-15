package com.outsider.virtual.concert.command.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.song.command.domain.repository.SongRepository;
import com.outsider.virtual.useractivity.command.domain.repository.ConcertCollectionRepository;
import com.outsider.virtual.user.exception.NotExistException;
import com.outsider.virtual.user.exception.NotMineException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConcertDeleteService {

    private final ConcertRepository concertRepository;
    private final SongRepository songRepository;
    private final ConcertCollectionRepository concertCollectionRepository;

    public ConcertDeleteService(
            ConcertRepository concertRepository,
            SongRepository songRepository,
            ConcertCollectionRepository concertCollectionRepository) {
        this.concertRepository = concertRepository;
        this.songRepository = songRepository;
        this.concertCollectionRepository = concertCollectionRepository;
    }

    public void delete(Long userId, Long id) {
        Concert concert = concertRepository.findById(id)
                .orElseThrow(NotExistException::new);
                
        if (!concert.getUserId().equals(userId)) {
            throw new NotMineException();
        }

        // concert_collection 테이블의 데이터를 먼저 삭제
        concertCollectionRepository.deleteByConcertId(id);

        // 해당 콘서트에 속�� 노래들을 삭제
        songRepository.deleteByConcertId(id);
        
        // 마지막으로 콘서트 삭제
        concertRepository.deleteById(id);
    }
}
