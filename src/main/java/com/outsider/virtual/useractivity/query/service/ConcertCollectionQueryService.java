package com.outsider.virtual.useractivity.query.service;


import com.outsider.virtual.useractivity.command.domain.repository.ConcertCollectionRepository;
import com.outsider.virtual.useractivity.query.dto.CollectionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertCollectionQueryService {

    private final ConcertCollectionRepository concertCollectionRepository;



    public Page<CollectionDTO> getUserCollections(Long userId, Pageable pageable) {
        return concertCollectionRepository.findUserCollections(userId, pageable);
    }
}
