package com.outsider.virtual.useractivity.command.domain.repository;


import com.outsider.virtual.useractivity.command.domain.aggregate.ConcertCollection;
import com.outsider.virtual.useractivity.query.dto.CollectionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConcertCollectionRepository extends JpaRepository<ConcertCollection, Long>  {
    List<ConcertCollection> findByUserId(Long userId);
    boolean existsByUserIdAndConcertId(Long userId, Long concertId);

    @Query("SELECT new com.outsider.virtual.useractivity.query.dto.CollectionDTO(" +
            "c.concert.id, c.concert.name, c.concert.img, " +
            "c.concert.concertDate, c.concert.startTime, " +
            "(SELECT u.userName FROM User u WHERE u.id = c.concert.userId), " +
            "(SELECT u.userName FROM User u WHERE u.id = c.user.id)) " +
            "FROM ConcertCollection c " +
            "WHERE c.user.id = :userId")
    Page<CollectionDTO> findUserCollections(@Param("userId") Long userId, Pageable pageable);

    Optional<ConcertCollection> findByUserIdAndConcertId(Long userId, Long concertId);
    @Transactional
    void deleteByConcertId(Long concertId);

}

