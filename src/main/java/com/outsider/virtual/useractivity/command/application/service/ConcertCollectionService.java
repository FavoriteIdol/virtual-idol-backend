package com.outsider.virtual.useractivity.command.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;
import com.outsider.virtual.useractivity.command.domain.aggregate.ConcertCollection;
import com.outsider.virtual.useractivity.command.domain.repository.ConcertCollectionRepository;
import com.outsider.virtual.useractivity.command.domain.repository.ViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConcertCollectionService {

    private final ConcertCollectionRepository concertCollectionRepository;
    private final UserCommandRepository userCommandRepository;
    private final ConcertRepository concertRepository;


    @Transactional
    public Long collectConcert(Long userId, Long concertId) {
        // 사용자와 콘서트가 존재하는지 검증
        User user = userCommandRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));

        Concert concert = concertRepository.findById(concertId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid concert ID: " + concertId));

        // 시청 시간이 충분한지 확인
//        if (!hasWatchedForRequiredDuration(userId, concertId)) {
//            throw new IllegalStateException("User has not watched the concert long enough to collect.");
//        }

        // 콘서트 수집 생성
        ConcertCollection collection = new ConcertCollection();
        collection.setUser(user);
        collection.setConcert(concert);
        collection.setCollectedDate(LocalDateTime.now());

        concertCollectionRepository.save(collection);
        return collection.getId();
    }
}
