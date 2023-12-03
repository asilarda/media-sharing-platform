package com.mediashare.videoplatform.repository;


import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.model.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RatingRepositoryTest extends AbstractPostgresContainerBaseTest {
    private @Autowired RatingRepository ratingRepository;

    @Test
    public void testRatingCreation() {
        Rating rating = new Rating();
        Rating savedRating = ratingRepository.save(rating);
        assertThat(savedRating).isNotNull();
    }
}