package com.outsider.virtual.stage.command.application.mapper;


import com.outsider.virtual.stage.command.application.dto.StageCreateDTO;
import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StageCreateMapper {

    @Mapping(target = "id", ignore = true) // 새로운 엔티티이므로 ID는 자동 생성되거나 무시
    @Mapping(target = "userId", source = "userId") // userId를 명시적으로 매핑
    Stage toEntity(StageCreateDTO dto, Long userId);
}
