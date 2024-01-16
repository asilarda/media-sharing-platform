package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.model.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VideoRepositoryTest extends AbstractPostgresContainerBaseTest {
    private @Autowired VideoRepository videoRepository;

    @Test
    public void testVideoCreation() {
        Video video = new Video();
        video.setTitle("Test Video");

        Video savedVideo = videoRepository.save(video);
        assertThat(savedVideo).isNotNull();
        assertThat(savedVideo.getVideoID()).isNotNull();
        assertThat(savedVideo.getTitle()).isEqualTo("Test Video");
    }

    @Test
    public void testVideoRetrieval() {
        Video video = new Video();
        video.setTitle("Test Video");
        video.setViews(1_000_000);
        Video savedVideo = videoRepository.save(video);

        Optional<Video> foundVideo = videoRepository.findById(savedVideo.getVideoID());
        assertThat(foundVideo).isPresent();
        assertThat(foundVideo.get().getDescription()).isEqualTo("Test Video");
        assertThat(savedVideo.getViews()).isEqualTo(1_000_000);
    }

    @Test
    public void testVideoUpdate() {
        Video video = new Video();
        video.setTitle("Test Video");
        video.setViews(1_000_000);
        Video savedVideo = videoRepository.save(video);

        savedVideo.setTitle("Updated Video");
        savedVideo.setViews(50_000);
        Video updatedVideo = videoRepository.save(savedVideo);
        assertThat(updatedVideo.getTitle()).isEqualTo("Updated Video");
        assertThat(updatedVideo.getViews()).isEqualTo(50_000);
    }

    @Test
    public void testVideoDeletion() {
        Video video = new Video();
        video.setTitle("Test Video");
        video.setViews(1_000_000);
        Video savedVideo = videoRepository.save(video);

        videoRepository.delete(savedVideo);
        Optional<Video> deletedVideo = videoRepository.findById(savedVideo.getVideoID());
        assertThat(deletedVideo).isEmpty();
    }
}