package com.mediashare.videoplatform.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChannelDTO {
    private Long videoID;
    private String name;
    private String description;
    private String profilePicture;
    private String backgroundImage;
    private LocalDateTime creationDate;
}
