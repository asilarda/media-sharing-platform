package com.mediashare.videoplatform.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Setter
@Getter
public class VideoDTO {
    private Long videoID;

    private String title;
    private String description;
    private LocalDateTime uploadDate;

    private Duration duration;
    private Integer views;
    private String videoURL;

}
