package com.mediashare.videoplatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channelID;

    private String name;
    private String description;
    private String profilePicture;
    private String backgroundImage;

    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "channel")
    private Set<Video> videos;

}
