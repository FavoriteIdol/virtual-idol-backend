package com.outsider.virtual.concert.query.domain.repository;

import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.query.application.dto.PerformanceDTO;
import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import com.outsider.virtual.user.command.domain.aggregate.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PerformanceMapper {

//    @Mapping(source = "concert.id", target = "id")                // Concert 엔티티의 id를 사용
//    @Mapping(source = "concert.name", target = "name")
//    @Mapping(source = "concert.img", target = "img")
//    @Mapping(source = "concert.concertDate", target = "concertDate")
//    @Mapping(source = "concert.startTime", target = "startTime")
//    @Mapping(source = "concert.ticketPrice", target = "ticketPrice")
//    @Mapping(source = "concert.stageId", target = "stageId")
//    PerformanceDTO toDTO(Concert concert, User user);


    @Mapping(source = "concert.id", target = "id")
    @Mapping(source = "concert.name", target = "name")
    @Mapping(source = "concert.img", target = "img")
    @Mapping(source = "concert.concertDate", target = "concertDate")
    @Mapping(source = "concert.startTime", target = "startTime")
    @Mapping(source = "concert.ticketPrice", target = "ticketPrice")
    @Mapping(source = "stage.name", target = "stageName")
    @Mapping(source = "stage.img", target = "stageImg")
    PerformanceDTO toDTO(Concert concert, Stage stage);
}
