package com.outsider.virtual.concert.command.domain.aggregate;

import com.outsider.virtual.util.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
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
    private Integer ticketPrice;

    @Column(name="people_scale")
    private Integer peopleScale;

    @ElementCollection
    @CollectionTable(
        name = "concert_songs",
        joinColumns = @JoinColumn(name = "concert_id")
    )
    @Column(name = "song_id")
    private List<Long> songIds = new ArrayList<>();

    public Integer getPeopleScale() {
        return peopleScale;
    }

    public void setPeopleScale(Integer peopleScale) {
        this.peopleScale = peopleScale;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
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

    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }

    // Constructors
    public Concert() {}

    public Concert(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {  // setId 메서드 추가
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
