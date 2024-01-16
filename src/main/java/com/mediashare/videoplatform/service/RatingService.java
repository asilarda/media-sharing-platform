package com.mediashare.videoplatform.service;

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
    private final static Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Transactional(readOnly = true)
    public List<Rating> findAllRatings() {
        logger.info("Fetching all ratings");
        return ratingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Rating> findRatingById(Long id) {
        logger.info("Fetching rating with ID: {}", id);
        return ratingRepository.findById(id);
    }

    @Transactional
    public Rating saveRating(Rating rating) {
        logger.info("Saving new Rating");
        return ratingRepository.save(rating);
    }

    @Transactional
    public void deleteRating(Long id) {
        logger.info("Deleting Rating with ID: {}", id);
        ratingRepository.deleteById(id);
    }
}
