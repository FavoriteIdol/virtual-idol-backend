package com.outsider.virtual.concert.query.application.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.outsider.virtual.song.query.application.dto.SongDTO;

@Data
public class ConcertInfoDTO {
    private Long id;
    private String name;
    private String img;
    private LocalDate concertDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer appearedVFX;
    private Integer feverVFX;
    private Long userId;
    private String userName;
    private Long stageId;
    private String ticketPrice;
    private String peopleScale;
    private List<SongDTO> songs;
}
