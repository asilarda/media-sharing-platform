package com.mediashare.videoplatform.controller;

import com.mediashare.videoplatform.dto.UserDTO;
import com.mediashare.videoplatform.model.User;
import com.mediashare.videoplatform.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        logger.debug("GET request for user with ID: {}", id);
        try {
            User user = userService.findUserById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.error("Failed to get user with ID: {}", id, e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        logger.debug("POST request to create a user");
        try {
            UserDTO createdUser = userService.saveUser(userDTO);
            logger.info("Created a new user with ID: {}", createdUser.getUserID());
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            logger.error("Failed to create user", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        logger.debug("DELETE request for user with ID: {}", id);
        try {
            userService.deleteUser(id);
            logger.info("Deleted user with ID: {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete user with ID: {}", id, e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        logger.debug("PUT request to update user with ID: {}", id);
        try {
            userDTO.setUserID(id);
            UserDTO updatedUser = userService.updateUser(userDTO);
            logger.info("Updated user with ID: {}", updatedUser.getUserID());
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            logger.error("Failed to update user with ID: {}", id, e);
            throw e;
        }
    }
}
