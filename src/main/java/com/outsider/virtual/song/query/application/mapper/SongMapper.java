package com.outsider.virtual.song.query.application.mapper;

import com.outsider.virtual.song.command.domain.aggregate.Song;
import com.outsider.virtual.song.query.application.dto.SongDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongDTO toDTO(Song song);
} 