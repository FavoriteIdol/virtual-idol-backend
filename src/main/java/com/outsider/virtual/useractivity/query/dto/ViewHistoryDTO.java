package com.outsider.virtual.useractivity.query.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViewHistoryDTO {
    private Long concertId;
    private String concertName;
    private String concertImage;
    private LocalDateTime viewDate;

    public ViewHistoryDTO(Long concertId, String concertName, String concertImage, LocalDateTime viewDate) {
        this.concertId = concertId;
        this.concertName = concertName;
        this.concertImage = concertImage;
        this.viewDate = viewDate;
    }
}
