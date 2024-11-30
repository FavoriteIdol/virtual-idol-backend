package com.outsider.virtual.user.query.application.dto;

import com.outsider.virtual.user.command.domain.aggregate.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toUserResponseDTO(User user);
}
