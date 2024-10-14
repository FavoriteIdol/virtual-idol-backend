package com.outsider.virtual.file.command.domain.repository;

import com.outsider.virtual.file.command.domain.aggregate.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {
}