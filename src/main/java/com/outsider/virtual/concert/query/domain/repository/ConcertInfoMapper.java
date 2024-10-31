package com.outsider.virtual.concert.query.domain.repository;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.query.application.dto.ConcertDTO;
import com.outsider.virtual.concert.query.application.dto.ConcertInfoDTO;
import com.outsider.virtual.user.command.domain.aggregate.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ConcertInfoMapper {
    @Mapping(source = "concert.id", target = "id")
    @Mapping(source = "concert.name", target = "name")
    @Mapping(source = "concert.concertDate", target = "concertDate")
    @Mapping(source = "concert.startTime", target = "startTime")
    @Mapping(source = "concert.endTime", target = "endTime")
    @Mapping(source = "concert.appearedVFX", target = "appearedVFX")
    @Mapping(source = "concert.feverVFX", target = "feverVFX")
    @Mapping(source = "concert.img", target = "img")
    @Mapping(source = "concert.userId", target = "userId")
    ConcertInfoDTO toDTO(Concert concert);


    @AfterMapping
    default void populateUserFields(@MappingTarget ConcertDTO stageDTO, User user) {
        if (user != null) {
            stageDTO.setUserName(user.getUserName());
            stageDTO.setUserId(user.getId());
        }
    }
}
