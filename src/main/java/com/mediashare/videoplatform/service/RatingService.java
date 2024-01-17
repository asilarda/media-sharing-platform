package com.mediashare.videoplatform.service;

import com.mediashare.videoplatform.dto.RatingDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mediashare.videoplatform.model.Rating;
import com.mediashare.videoplatform.repository.RatingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final ModelMapper modelMapper;

    private final static Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    public RatingService(RatingRepository ratingRepository, ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<Rating> findAllRatings() {
        logger.debug("Retrieving all ratings");
        return ratingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Rating> findRatingById(Long id) {
        logger.debug("Retrieving rating with ID: {}", id);
        return ratingRepository.findById(id);
    }

    @Transactional
    public void deleteRating(Long id) {
        logger.debug("Deleting rating with ID: {}", id);
        ratingRepository.deleteById(id);
        logger.info("Rating deleted with ID: {}", id);
    }

    @Transactional
    public RatingDTO saveRating(RatingDTO ratingDTO) {
        logger.debug("Saving new rating");
        try {
            Rating rating = modelMapper.map(ratingDTO, Rating.class);
            Rating savedRating = ratingRepository.save(rating);
            logger.info("Rating saved with ID: {}", savedRating.getRatingID());
            return modelMapper.map(savedRating, RatingDTO.class);
        } catch (Exception e) {
            logger.error("Error saving rating: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public RatingDTO updateRating(RatingDTO ratingDTO) {
        logger.debug("Updating rating with ID: {}", ratingDTO.getRatingID());
        if (ratingDTO.getRatingID() == null) {
            logger.error("Rating ID must not be null for update operation");
            throw new IllegalArgumentException("Rating ID must not be null for update operation");
        }
        try {
            Rating existingRating = ratingRepository.findById(ratingDTO.getRatingID())
                    .orElseThrow(() -> new IllegalArgumentException("Rating with ID " + ratingDTO.getRatingID() + " not found"));
            modelMapper.map(ratingDTO, existingRating);
            Rating updatedRating = ratingRepository.save(existingRating);
            logger.info("Rating updated with ID: {}", updatedRating.getRatingID());
            return modelMapper.map(updatedRating, RatingDTO.class);
        } catch (IllegalArgumentException e) {
            logger.error("Error updating rating: Rating not found", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error updating rating", e);
            throw e;
        }
    }
}
