package com.mediashare.videoplatform.controller;

import com.mediashare.videoplatform.dto.VideoDTO;
import com.mediashare.videoplatform.exception.VideoNotFoundException;
import com.mediashare.videoplatform.model.Video;
import com.mediashare.videoplatform.service.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VideoControllerTest {

    @Mock
    private VideoService videoService;

    @InjectMocks
    private VideoController videoController;

    private Video video;
    private VideoDTO videoDTO;

    @BeforeEach
    void setUp() {
        video = new Video();
        videoDTO = new VideoDTO();
    }

    @Test
    void testGetVideo_Found() {
        Long id = 1L;
        when(videoService.findVideoById(id)).thenReturn(Optional.of(video));

        ResponseEntity<Video> response = videoController.getVideo(id);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(video);
    }

    @Test
    void testGetVideo_NotFound() {
        Long id = 1L;
        when(videoService.findVideoById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> videoController.getVideo(id))
                .isInstanceOf(VideoNotFoundException.class)
                .hasMessageContaining("Video not found with ID: " + id);
    }

    @Test
    void testCreateVideo() {
        when(videoService.saveVideo(videoDTO)).thenReturn(videoDTO);

        ResponseEntity<VideoDTO> response = videoController.createVideo(videoDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(videoDTO);
    }

    @Test
    void testUpdateVideo() {
        Long id = 1L;
        when(videoService.updateVideo(videoDTO)).thenReturn(videoDTO);

        ResponseEntity<VideoDTO> response = videoController.updateVideo(id, videoDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(videoDTO);
    }

    @Test
    void testDeleteVideo() {
        Long id = 1L;
        doNothing().when(videoService).deleteVideo(id);

        ResponseEntity<?> response = videoController.deleteVideo(id);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        verify(videoService, times(1)).deleteVideo(id);
    }
}
