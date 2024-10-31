package com.outsider.virtual.useractivity.query.service;

import com.outsider.virtual.useractivity.command.domain.repository.ViewHistoryRepository;
import com.outsider.virtual.useractivity.query.dto.ViewHistoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewHistoryQueryService {

    private final ViewHistoryRepository viewHistoryRepository;

    public Page<ViewHistoryDTO> getUserViewHistory(Long userId, Pageable pageable) {
        return viewHistoryRepository.findByUserId(userId, pageable);
    }
}
