package com.outsider.virtual.concert.query.domain.repository;

import com.outsider.virtual.concert.command.domain.aggregate.QConcert;
import com.outsider.virtual.concert.query.application.dto.ConcertDTO;
import com.outsider.virtual.concert.query.application.dto.PerformanceDTO;
import com.outsider.virtual.stage.command.domain.aggregate.QStage;
import com.outsider.virtual.user.command.domain.aggregate.QUser;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

public class ConcertRepositoryCustomImpl implements ConcertRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private final ConcertMapper concertMapper; // MapStruct Mapper
    private final PerformanceMapper performanceMapper; // MapStruct Mapper


    public ConcertRepositoryCustomImpl(EntityManager entityManager, ConcertMapper concertMapper, PerformanceMapper performanceMapper) {
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.concertMapper = concertMapper;
        this.performanceMapper = performanceMapper;
    }




    public Page<ConcertDTO> findAllConcertWithUsers(Pageable pageable) {
        QConcert concert = QConcert.concert;
        QUser user = QUser.user;
        // Fetch the Stage entities with joined User information
        List<Tuple> tuples = queryFactory
                .select(concert, user)
                .from(concert)
                .leftJoin(user).on(concert.userId.eq(user.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        // Map Stage and User entities to StageDTO using MapStruct
        List<ConcertDTO> results = tuples.stream()
                .map(tuple -> concertMapper.toDTO(tuple.get(concert), tuple.get(user)))
                .collect(Collectors.toList());
        long total = queryFactory
                .select(concert.count())
                .from(concert)
                .fetchOne();

        return new PageImpl<>(results, pageable, total);
    }

//    @Override
//    public List<ConcertDTO> findConcertsByYearAndMonth(int year, int month) {
//        QConcert concert = QConcert.concert;
//        QUser user = QUser.user;
//
//        LocalDate startOfMonth = YearMonth.of(year, month).atDay(1);
//        LocalDate endOfMonth = YearMonth.of(year, month).atEndOfMonth();
//
//        List<Tuple> tuples = queryFactory
//                .select(concert, user)
//                .from(concert)
//                .leftJoin(user).on(concert.userId.eq(user.id))
//                .where(concert.concertDate.between(startOfMonth, endOfMonth))
//                .fetch();
//
//        return tuples.stream()
//                .map(tuple -> concertMapper.toDTO(tuple.get(concert), tuple.get(user)))
//                .collect(Collectors.toList());
//    }

    @Override
    public List<PerformanceDTO> findConcertsByYearAndMonth(int year, int month) {
        QConcert concert = QConcert.concert;
        QStage stage = QStage.stage;

        LocalDate startOfMonth = YearMonth.of(year, month).atDay(1);
        LocalDate endOfMonth = YearMonth.of(year, month).atEndOfMonth();

        List<Tuple> tuples = queryFactory
                .select(concert, stage)
                .from(concert)
                .leftJoin(stage).on(concert.stageId.eq(stage.id))
                .where(concert.concertDate.between(startOfMonth, endOfMonth))
                .fetch();

        return tuples.stream()
                .map(tuple -> performanceMapper.toDTO(tuple.get(concert), tuple.get(stage)))
                .collect(Collectors.toList());
    }

    public Page<ConcertDTO> findConcertsByUserId(Long userId, Pageable pageable) {
        QConcert concert = QConcert.concert;
        QUser user = QUser.user;

        // Fetch the Concert entities with joined User information filtered by userId
        List<Tuple> tuples = queryFactory
                .select(concert, user)
                .from(concert)
                .leftJoin(user).on(concert.userId.eq(user.id))
                .where(user.id.eq(userId)) // Filter by userId
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Map Concert and User entities to ConcertDTO using MapStruct
        List<ConcertDTO> results = tuples.stream()
                .map(tuple -> concertMapper.toDTO(tuple.get(concert), tuple.get(user)))
                .collect(Collectors.toList());

        long total = queryFactory
                .select(concert.count())
                .from(concert)
                .leftJoin(user).on(concert.userId.eq(user.id)) // 명시적으로 user와 조인
                .where(user.id.eq(userId)) // 필터 적용
                .fetchOne();

        return new PageImpl<>(results, pageable, total);
    }

    // 임박한 공연을 가져오는 메서드 수정
    @Override
    public List<PerformanceDTO> findImminentConcerts(int limit) {
        QConcert concert = QConcert.concert;
        QStage stage = QStage.stage;

        List<Tuple> tuples = queryFactory
                .select(concert, stage)
                .from(concert)
                .leftJoin(stage).on(concert.stageId.eq(stage.id))
                .orderBy(Expressions.numberTemplate(
                                Long.class,
                                "abs(datediff({0}, {1}))", concert.concertDate, LocalDate.now()
                        ).asc(),
                        concert.startTime.asc())
                .limit(limit)  // 최대 limit 개수만큼 가져오기
                .fetch();

        return tuples.stream()
                .map(tuple -> performanceMapper.toDTO(tuple.get(concert), tuple.get(stage)))
                .collect(Collectors.toList());
    }
}
