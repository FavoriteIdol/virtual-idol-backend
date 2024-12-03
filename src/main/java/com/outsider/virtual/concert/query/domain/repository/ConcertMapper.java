package com.outsider.virtual.concert.query.domain.repository;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.query.application.dto.ConcertDTO;
import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import com.outsider.virtual.stage.query.application.dto.StageDTO;
import com.outsider.virtual.user.command.domain.aggregate.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ConcertMapper {
    @Mapping(source = "concert.id", target = "id")
    @Mapping(source = "concert.name", target = "name")
    @Mapping(source = "concert.concertDate", target = "concertDate")
    @Mapping(source = "concert.startTime", target = "startTime")
    @Mapping(source = "concert.endTime", target = "endTime")
    @Mapping(source = "concert.appearedVFX", target = "appearedVFX")
    @Mapping(source = "concert.feverVFX", target = "feverVFX")
    @Mapping(source = "concert.img", target = "img")
    @Mapping(source = "user.userName", target = "userName")
    @Mapping(source = "user.id", target = "userId")
    ConcertDTO toDTO(Concert concert, User user);

    @AfterMapping
    default void populateUserFields(@MappingTarget ConcertDTO stageDTO, User user) {
        if (user != null) {
            stageDTO.setUserName(user.getUserName());
            stageDTO.setUserId(user.getId());
        }
    }
}
