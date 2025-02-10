package com.mediashare.videoplatform.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentDTO {
    private Long commentID;
    private String text;
    private LocalDateTime date;

}
