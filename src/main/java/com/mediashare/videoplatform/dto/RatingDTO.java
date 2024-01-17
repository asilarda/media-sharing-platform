package com.mediashare.videoplatform.dto;

import com.mediashare.videoplatform.enums.RatingType;

import java.time.LocalDateTime;

public class RatingDTO {
    private Long ratingID;
    private LocalDateTime date;

    private RatingType type;
}
