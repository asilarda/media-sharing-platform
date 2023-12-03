package com.mediashare.videoplatform.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Set<Channel> getOwnedChannels() {
        return ownedChannels;
    }

    public void setOwnedChannels(Set<Channel> ownedChannels) {
        this.ownedChannels = ownedChannels;
    }

    public Set<Playlist> getCreatedPlaylists() {
        return createdPlaylists;
    }

    public void setCreatedPlaylists(Set<Playlist> createdPlaylists) {
        this.createdPlaylists = createdPlaylists;
    }

    public Set<Comment> getWrittenComments() {
        return writtenComments;
    }

    public void setWrittenComments(Set<Comment> writtenComments) {
        this.writtenComments = writtenComments;
    }

    public Set<Rating> getGivenRatings() {
        return givenRatings;
    }

    public void setGivenRatings(Set<Rating> givenRatings) {
        this.givenRatings = givenRatings;
    }
}
