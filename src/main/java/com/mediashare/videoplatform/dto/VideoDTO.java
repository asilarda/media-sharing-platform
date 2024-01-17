package com.mediashare.videoplatform.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public class VideoDTO {
    private Long videoID;

    private String title;
    private String description;
    private LocalDateTime uploadDate;

    private Duration duration;
    private Integer views;
    private String videoURL;

    public Long getVideoID() {
        return videoID;
    }

    public void setVideoID(Long videoID) {
        this.videoID = videoID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
