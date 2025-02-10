package com.mediashare.videoplatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String username;
    private String password;
    private String email;
    private String profilePicture;
    private String biography;

    private LocalDateTime registrationDate;

    // User to Channel relationship
    @OneToMany(mappedBy = "owner")
    private Set<Channel> ownedChannels;

    // User to Playlist relationship
    @OneToMany(mappedBy = "creator")
    private Set<Playlist> createdPlaylists;

    // User to Comment relationship
    @OneToMany(mappedBy = "author")
    private Set<Comment> writtenComments;

    // User to Rating relationship
    @OneToMany(mappedBy = "user")
    private Set<Rating> givenRatings;

}
