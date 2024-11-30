package com.outsider.virtual.useractivity.query.dto;


import com.outsider.virtual.useractivity.command.domain.aggregate.ConcertCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
public class CollectionDTO {

    private Long concertId;
    private String concertName;
    private String concertImage;
    private LocalDateTime collectedDate;
    private String artist;
    private String audience;

    public CollectionDTO(Long concertId, String concertName, String concertImage, LocalDateTime collectedDate,String artist,String audience) {
        this.concertId = concertId;
        this.concertName = concertName;
        this.concertImage = concertImage;
        this.collectedDate = collectedDate;
        this.artist = artist;
        this.audience = audience;
    }
}
