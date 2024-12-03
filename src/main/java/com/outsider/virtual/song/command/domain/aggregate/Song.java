package com.outsider.virtual.song.command.domain.aggregate;

import com.outsider.virtual.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Song extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long id;

    @Column(name = "song_url", nullable = false, length = 512)
    private String url;

    @Column(name = "song_title", nullable = false)
    private String title;

    @Column(name = "artist_id")
    private Long artistId;

    @Column(name = "duration")
    private Integer duration;
} 