package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.enums.RatingType;
import com.mediashare.videoplatform.model.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RatingRepositoryTest extends AbstractPostgresContainerBaseTest {
    private @Autowired RatingRepository ratingRepository;

    @Test
    public void testRatingCreation() {
        Rating rating = new Rating();
        rating.setType(RatingType.ThumbsUp);

        Rating savedRating = ratingRepository.save(rating);
        assertThat(savedRating).isNotNull();
        assertThat(savedRating.getRatingID()).isNotNull();
        assertThat(savedRating.getType()).isEqualTo(RatingType.ThumbsUp);
    }

    @Test
    public void testRatingRetrieval() {
        Rating rating = new Rating();
        rating.setType(RatingType.ThumbsDown);
        Rating savedRating = ratingRepository.save(rating);

        Optional<Rating> foundRating = ratingRepository.findById(savedRating.getRatingID());
        assertThat(foundRating).isPresent();
        assertThat(foundRating.get().getType()).isEqualTo(RatingType.ThumbsDown);
    }

    @Test
    public void testRatingUpdate() {
        Rating rating = new Rating();
        rating.setType(RatingType.ThumbsUp);
        Rating savedRating = ratingRepository.save(rating);

        rating.setType(RatingType.ThumbsDown);
        Rating updatedRating = ratingRepository.save(savedRating);
        assertThat(updatedRating.getType()).isEqualTo(RatingType.ThumbsDown);
    }

    @Test
    public void testRatingDeletion() {
        Rating rating = new Rating();
        rating.setType(RatingType.ThumbsDown);
        Rating savedRating = ratingRepository.save(rating);

        ratingRepository.delete(savedRating);
        Optional<Rating> deletedRating = ratingRepository.findById(savedRating.getRatingID());
        assertThat(deletedRating).isEmpty();
    }
}