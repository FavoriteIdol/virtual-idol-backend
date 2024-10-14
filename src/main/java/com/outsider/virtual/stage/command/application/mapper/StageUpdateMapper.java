package com.outsider.virtual.stage.command.application.mapper;


import com.outsider.virtual.stage.command.application.dto.StageUpdateDTO;
import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StageUpdateMapper   {
    void updateStageFromDto(StageUpdateDTO dto, @MappingTarget Stage entity);
}
