package com.mediashare.videoplatform.dto;

public class PlaylistDTO {
    private Long playlistID;

    private String title;

    public Long getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(Long playlistID) {
        this.playlistID = playlistID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
