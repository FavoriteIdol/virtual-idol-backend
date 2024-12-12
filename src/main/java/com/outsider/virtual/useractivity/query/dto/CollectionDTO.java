package com.outsider.virtual.useractivity.query.dto;


import com.outsider.virtual.useractivity.command.domain.aggregate.ConcertCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CollectionDTO {

    private Long concertId;
    private String concertName;
    private String concertImage;
    private LocalDate concertDate;
    private LocalTime startTime;
    private String artist;
    private String audience;

    public CollectionDTO(Long concertId, String concertName, String concertImage, LocalDate concertDate, LocalTime startTime, String artist, String audience) {
        this.concertId = concertId;
        this.concertName = concertName;
        this.concertImage = concertImage;
        this.concertDate = concertDate;
        this.startTime = startTime;
        this.artist = artist;
        this.audience = audience;
    }
}
