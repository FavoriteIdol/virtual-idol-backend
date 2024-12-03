package com.outsider.virtual.song.command.application.mapper;

import com.outsider.virtual.song.command.application.dto.SongCreateDTO;
import com.outsider.virtual.song.command.domain.aggregate.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongCreateMapper {
    @Mapping(target = "id", ignore = true)
    Song toEntity(SongCreateDTO dto);
} 