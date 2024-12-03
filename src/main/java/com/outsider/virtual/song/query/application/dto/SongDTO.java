package com.outsider.virtual.song.query.application.dto;

import lombok.Data;

@Data
public class SongDTO {
    private Long id;
    private String url;
    private String title;
    private Long artistId;
    private Integer duration;
} 