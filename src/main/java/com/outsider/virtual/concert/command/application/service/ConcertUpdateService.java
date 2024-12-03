package com.outsider.virtual.concert.command.application.service;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.concert.command.application.dto.ConcertUpdateDTO;
import com.outsider.virtual.user.exception.NotExistException;
import com.outsider.virtual.user.exception.NotMineException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class ConcertUpdateService {

    private final ConcertRepository concertRepository;

    public ConcertUpdateService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public void update(Long userId, Long id, ConcertUpdateDTO dto) {
        Concert concert = concertRepository.findById(id)
            .orElseThrow(NotExistException::new);

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
            concert.updateSongs(dto.getSongIds());
        }

        concertRepository.save(concert);
    }
}
