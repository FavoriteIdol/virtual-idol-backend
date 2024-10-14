package com.outsider.virtual.stage.query.domain.repository;

import com.outsider.virtual.stage.query.application.dto.StageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StageRepositoryCustom {
    Page<StageDTO> findAllStagesWithUsers(Pageable pageable);
}
