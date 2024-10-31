package com.outsider.virtual.concert.command.application.mapper;

import com.outsider.virtual.concert.command.application.dto.ConcertCreateDTO;
import com.outsider.virtual.concert.command.application.dto.ConcertUpdateDTO;
import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ConcertUpdateMapper {

    void toEntity(ConcertUpdateDTO dto, @MappingTarget Concert concert);
}
