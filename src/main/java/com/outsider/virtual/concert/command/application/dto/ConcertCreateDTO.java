package com.outsider.virtual.concert.command.application.dto;

import com.outsider.virtual.concert.command.domain.aggregate.AppearedVFX;
import com.outsider.virtual.concert.command.domain.aggregate.FeverVFX;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ConcertCreateDTO {
    @Schema(description = "콘서트 이름", example = "My Concert")
    private String name;

    @Schema(description = "콘서트 이미지 URL", example = "http://example.com/concert.jpg")
    private String img;

    @Schema(description = "콘서트 날짜", example = "2024-10-14", type = "string", format = "date")
    private LocalDate concertDate;

    @Schema(description = "콘서트 시작 시간", example = "18:00:00", type = "string", format = "time")
    private LocalTime startTime;

    @Schema(description = "콘서트 종료 시간", example = "20:00:00", type = "string", format = "time")
    private LocalTime endTime;

    @Schema(description = "등장 효과", example = "1")
    private Integer appearedVFX;

    @Schema(description = "Fever 효과", example = "1")
    private Integer feverVFX;

    @Schema(description = "스테이지 ID", example = "1")
    private Long stageId;

    @Schema(description = "티켓 가격", example = "1000")
    private  Integer ticketPrice;

    @Schema(description = "사람 규모", example = "30")
    private Integer peopleScale;
}
