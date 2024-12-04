package com.outsider.virtual.song.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SongCreateDTO {
    @Schema(description = "노래 URL", example = "http://example.com/song.mp3", required = true)
    private String url;

    @Schema(description = "노래 제목", example = "노래 제목", required = true)
    private String title;

    @Schema(hidden = true)
    private Long artistId;

    @Schema(hidden = true)
    private Long concertId;

    @Schema(description = "재생 시간(초)", example = "180")
    private Integer duration;
} 