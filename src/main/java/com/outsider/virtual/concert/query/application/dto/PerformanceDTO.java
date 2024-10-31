package com.outsider.virtual.concert.query.application.dto;

import lombok.Data;

import java.time.LocalDate;

import java.time.LocalTime;

@Data
public class PerformanceDTO {
    private Long id;                // 공연 ID
    private String name;            // 공연 이름
    private String img;             // 공연 이미지 URL
    private LocalDate concertDate;  // 공연 날짜
    private LocalTime startTime;    // 공연 시작 시간
    private Integer ticketPrice;    // 티켓 가격
    private String stageName;       // 무대 이름
    private String stageImg;        // 무대 이미지 URL
}
