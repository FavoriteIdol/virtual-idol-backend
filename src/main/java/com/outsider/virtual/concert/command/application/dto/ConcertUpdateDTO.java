package com.outsider.virtual.concert.command.application.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ConcertUpdateDTO {
    private String name;
    private LocalDate concertDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String ticketPrice;
    private String peopleScale;
    private List<Long> songIds;
}
