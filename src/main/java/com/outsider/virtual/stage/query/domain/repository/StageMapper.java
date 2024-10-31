package com.outsider.virtual.stage.query.domain.repository;

import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import com.outsider.virtual.stage.query.application.dto.StageDTO;
import com.outsider.virtual.user.command.domain.aggregate.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface StageMapper {

    @Mapping(source = "stage.id", target = "id")
    @Mapping(source = "stage.name", target = "name")
    @Mapping(source = "stage.terrain", target = "terrain")
    @Mapping(source = "stage.sky", target = "sky")
    @Mapping(source = "stage.theme", target = "theme")
    @Mapping(source = "stage.specialEffect", target = "specialEffect")
    @Mapping(source = "user.userName", target = "userName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "stage.img", target = "img")
    StageDTO toStageDTO(Stage stage, User user);

    @AfterMapping
    default void populateUserFields(@MappingTarget StageDTO stageDTO, User user) {
        if (user != null) {
            stageDTO.setUserName(user.getUserName());
        }
    }
}
