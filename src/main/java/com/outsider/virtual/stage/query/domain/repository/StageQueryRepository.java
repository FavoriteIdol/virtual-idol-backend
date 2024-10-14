package com.outsider.virtual.stage.query.domain.repository;

import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import com.outsider.virtual.stage.query.application.dto.StageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StageQueryRepository extends JpaRepository<Stage, Long> ,StageRepositoryCustom  {

}
