package com.mediashare.videoplatform.controller;

import com.mediashare.videoplatform.dto.RatingDTO;
import com.mediashare.videoplatform.model.Rating;
import com.mediashare.videoplatform.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ratings")
public class RatingController {
    private final RatingService ratingService;
    private final static Logger logger = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRating(@PathVariable Long id) {
        logger.debug("GET request for rating with ID: {}", id);
        try {
            Rating rating = ratingService.findRatingById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Rating not found with ID: " + id));
            return ResponseEntity.ok(rating);
        } catch (Exception e) {
            logger.error("Failed to get rating with ID: {}", id, e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO ratingDTO) {
        logger.debug("POST request to create a rating");
        try {
            RatingDTO createdRating = ratingService.saveRating(ratingDTO);
            logger.info("Created a new rating with ID: {}", createdRating.getRatingID());
            return ResponseEntity.ok(createdRating);
        } catch (Exception e) {
            logger.error("Failed to create rating", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable Long id) {
        logger.debug("DELETE request for rating with ID: {}", id);
        try {
            ratingService.deleteRating(id);
            logger.info("Deleted rating with ID: {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete rating with ID: {}", id, e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingDTO> updateRating(@PathVariable Long id, @RequestBody RatingDTO ratingDTO) {
        logger.debug("PUT request to update rating with ID: {}", id);
        try {
            ratingDTO.setRatingID(id);
            RatingDTO updatedRating = ratingService.updateRating(ratingDTO);
            logger.info("Updated rating with ID: {}", updatedRating.getRatingID());
            return ResponseEntity.ok(updatedRating);
        } catch (Exception e) {
            logger.error("Failed to update rating with ID: {}", id, e);
            throw e;
        }
    }
}
