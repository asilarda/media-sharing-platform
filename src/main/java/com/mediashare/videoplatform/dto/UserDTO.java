package com.mediashare.videoplatform.dto;

import java.time.LocalDateTime;

public class UserDTO {
    private Long userID;

    private String username;
    private String password;
    private String email;
    private String profilePicture;
    private String biography;

    private LocalDateTime registrationDate;
}
