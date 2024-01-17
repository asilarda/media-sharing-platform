package com.mediashare.videoplatform.dto;

import com.mediashare.videoplatform.enums.RatingType;

import java.time.LocalDateTime;

public class RatingDTO {
    private Long ratingID;
    private LocalDateTime date;

    private RatingType type;

    public Long getRatingID() {
        return ratingID;
    }

    public void setRatingID(Long ratingID) {
        this.ratingID = ratingID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public RatingType getType() {
        return type;
    }

    public void setType(RatingType type) {
        this.type = type;
    }
}
