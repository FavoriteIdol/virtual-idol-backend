package com.outsider.virtual.stage.command.domain.repository;

import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, Long>{
}
