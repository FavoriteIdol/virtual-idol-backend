package com.outsider.virtual.stage.query.domain.repository;

import com.outsider.virtual.stage.command.domain.aggregate.QStage;
import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import com.outsider.virtual.stage.query.application.dto.StageDTO;
import com.outsider.virtual.user.command.domain.aggregate.QUser;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StageRepositoryCustomImpl implements StageRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final StageMapper stageMapper; // MapStruct Mapper

    public StageRepositoryCustomImpl(EntityManager entityManager, StageMapper stageMapper) {
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.stageMapper = stageMapper;
    }

    @Override
    public Page<StageDTO> findAllStagesWithUsers(Pageable pageable) {
        QStage stage = QStage.stage;
        QUser user = QUser.user;

        // Fetch the Stage entities with joined User information
        List<Tuple> tuples = queryFactory
                .select(stage, user)
                .from(stage)
                .leftJoin(user).on(stage.userId.eq(user.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        // Map Stage and User entities to StageDTO using MapStruct
        List<StageDTO> results = tuples.stream()
                .map(tuple -> stageMapper.toStageDTO(tuple.get(stage), tuple.get(user)))
                .collect(Collectors.toList());

        long total = queryFactory
                .select(stage.count())
                .from(stage)
                .fetchOne();

        return new PageImpl<>(results, pageable, total);
    }

}
