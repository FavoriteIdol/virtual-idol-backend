package com.outsider.virtual.useractivity.command.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;
import com.outsider.virtual.useractivity.command.domain.aggregate.ViewHistory;
import com.outsider.virtual.useractivity.command.domain.repository.ViewHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ViewHistoryService {

    private final ViewHistoryRepository viewHistoryRepository;
    private final UserCommandRepository userRepository;
    private final ConcertRepository concertRepository;



    @Transactional
    public Long createViewHistory(Long userId, Long concertId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Concert concert = concertRepository.findById(concertId).orElseThrow(() -> new EntityNotFoundException("Concert not found"));

        ViewHistory viewHistory = new ViewHistory();
        viewHistory.setUser(user);
        viewHistory.setConcert(concert);
        viewHistory.setViewDate(LocalDateTime.now());

        viewHistoryRepository.save(viewHistory);
        return viewHistory.getId();
    }
}
