package com.mediashare.videoplatform.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserDTO {
    private Long userID;

    private String username;
    private String password;
    private String email;
    private String profilePicture;
    private String biography;

    private LocalDateTime registrationDate;

}
