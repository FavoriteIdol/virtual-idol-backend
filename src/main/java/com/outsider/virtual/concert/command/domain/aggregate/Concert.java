package com.outsider.virtual.concert.command.domain.aggregate;

import com.outsider.virtual.song.command.domain.aggregate.Song;
import com.outsider.virtual.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Concert extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="concert_id")
    private Long id;

    @Column(name="concert_name")
    private String name;

    @Column(name="concert_img")
    private String img;

    @Column(name="concert_date")
    private LocalDate concertDate;

    @Column(name="concert_start_time")
    private LocalTime startTime;

    @Column(name="concert_end_time")
    private LocalTime endTime;

    @Column(name="appeared_vfx")
    private Integer appearedVFX;

    @Column(name="fever_vfx")
    private Integer feverVFX;

    @Column(name="user_id")
    private Long userId;

    @Column(name="stage_id")
    private Long stageId;

    @Column(name="ticket_price")
    private String ticketPrice;

    @Column(name="people_scale")
    private String peopleScale;

    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConcertSong> songs = new ArrayList<>();

    public List<Long> getSongIds() {
        if (this.songs == null) {
            return new ArrayList<>();
        }
        return this.songs.stream()
            .map(song -> song.getSong().getId())
            .collect(Collectors.toList());
    }

    // Constructors
    public Concert() {}

    public Concert(String name) {
        this.name = name;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Integer getAppearedVFX() {
        return appearedVFX;
    }

    public void setAppearedVFX(Integer appearedVFX) {
        this.appearedVFX = appearedVFX;
    }

    public Integer getFeverVFX() {
        return feverVFX;
    }

    public void setFeverVFX(Integer feverVFX) {
        this.feverVFX = feverVFX;
    }

    public LocalDate getConcertDate() {
        return concertDate;
    }

    public void setConcertDate(LocalDate concertDate) {
        this.concertDate = concertDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
