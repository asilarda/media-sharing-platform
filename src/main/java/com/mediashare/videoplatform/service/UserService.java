package com.mediashare.videoplatform.service;

import com.mediashare.videoplatform.dto.UserDTO;
import com.mediashare.videoplatform.model.User;
import com.mediashare.videoplatform.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        logger.debug("Retrieving all users");
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        logger.debug("Retrieving user with ID: {}", id);
        return userRepository.findById(id);
    }

    @Transactional
    public void deleteUser(Long id) {
        logger.debug("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        logger.info("User deleted with ID: {}", id);
    }

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        logger.debug("Saving new user");
        try {
            User user = modelMapper.map(userDTO, User.class);
            User savedUser = userRepository.save(user);
            logger.info("User saved with ID: {}", savedUser.getUserID());
            return modelMapper.map(savedUser, UserDTO.class);
        } catch (Exception e) {
            logger.error("Error saving user: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        logger.debug("Updating user with ID: {}", userDTO.getUserID());
        if (userDTO.getUserID() == null) {
            logger.error("User ID must not be null for update operation");
            throw new IllegalArgumentException("User ID must not be null for update operation");
        }
        try {
            User existingUser = userRepository.findById(userDTO.getUserID())
                    .orElseThrow(() -> new IllegalArgumentException("User with ID " + userDTO.getUserID() + " not found"));
            modelMapper.map(userDTO, existingUser);
            User updatedUser = userRepository.save(existingUser);
            logger.info("User updated with ID: {}", updatedUser.getUserID());
            return modelMapper.map(updatedUser, UserDTO.class);
        } catch (IllegalArgumentException e) {
            logger.error("Error updating user: User not found", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error updating user", e);
            throw e;
        }
    }
}
