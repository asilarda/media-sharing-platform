package com.mediashare.videoplatform.dto;

import com.mediashare.videoplatform.enums.RatingType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class RatingDTO {
    private Long ratingID;
    private LocalDateTime date;

    private RatingType type;

}
