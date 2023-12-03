package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.model.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VideoRepositoryTest extends AbstractPostgresContainerBaseTest {
    private @Autowired VideoRepository videoRepository;

    @Test
    public void testVideoCreation() {
        Video video = new Video();
        Video savedVideo = videoRepository.save(video);
        assertThat(savedVideo).isNotNull();
    }
}