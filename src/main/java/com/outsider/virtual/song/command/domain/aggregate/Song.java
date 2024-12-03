package com.outsider.virtual.song.command.domain.aggregate;

import com.outsider.virtual.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "songs")
public class Song extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;

    @Column(name = "artist_id")
    private Long artistId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "duration")
    private Integer duration;
} 