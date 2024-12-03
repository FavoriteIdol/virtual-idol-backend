package com.outsider.virtual.song.command.domain.aggregate;

import com.outsider.virtual.concert.command.domain.aggregate.ConcertSong;
import com.outsider.virtual.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConcertSong> concertSongs = new ArrayList<>();

    public Song() {}

    public Song(Long id) {
        this.id = id;
    }
} 