package com.outsider.virtual.stage.command.domain.repository;

import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StageRepository extends JpaRepository<Stage, Long>{
    // Stage 엔티티의 name 필드로 Stage 찾기
    Optional<Stage> findByName(String name);

}
