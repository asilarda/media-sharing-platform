package com.mediashare.videoplatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoID;

    private String title;
    private String description;
    private LocalDateTime uploadDate;

    private Duration duration;
    private Integer views;
    private String videoURL;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @OneToMany(mappedBy = "video")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "video")
    private Set<Rating> ratings;

}
