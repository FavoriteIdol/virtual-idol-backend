package com.outsider.virtual.concert.command.application.mapper;

import com.outsider.virtual.concert.command.application.dto.ConcertCreateDTO;
import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.stage.command.application.dto.StageCreateDTO;
import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ConcertCreateMapper {

    @Mapping(target = "id", ignore = true) // 새로운 엔티티이므로 ID는 자동 생성되거나 무시
    @Mapping(target = "userId", source = "userId") // userId를 명시적으로 매핑
    void toEntity(ConcertCreateDTO dto, Long userId, @MappingTarget Concert concert);
}
