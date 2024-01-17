package com.mediashare.videoplatform.service;

import com.mediashare.videoplatform.dto.VideoDTO;
import com.mediashare.videoplatform.exception.VideoNotFoundException;
import com.mediashare.videoplatform.model.Video;
import com.mediashare.videoplatform.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private VideoService videoService;

    private Video video;
    private VideoDTO videoDTO;

    @BeforeEach
    void setUp() {
        video = new Video();
        videoDTO = new VideoDTO();
        when(modelMapper.map(any(VideoDTO.class), eq(Video.class))).thenReturn(video);
        when(modelMapper.map(any(Video.class), eq(VideoDTO.class))).thenReturn(videoDTO);
    }

    @Test
    void testFindAllVideos() {
        when(videoRepository.findAll()).thenReturn(Arrays.asList(video));

        List<Video> result = videoService.findAllVideos();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(video);
    }

    @Test
    void whenFindAllVideos_thenShouldReturnListOfVideos() {
        when(videoRepository.findAll()).thenReturn(Collections.singletonList(video));

        List<Video> videos = videoService.findAllVideos();

        assertThat(videos).isNotEmpty();
        assertThat(videos.size()).isEqualTo(1);
    }

    @Test
    void testFindVideoById_Found() {
        Long id = 1L;
        when(videoRepository.findById(id)).thenReturn(Optional.of(video));

        Optional<Video> result = videoService.findVideoById(id);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(video);
    }

    @Test
    void whenFindVideoById_thenShouldReturnVideo() {
        Long id = 1L;
        when(videoRepository.findById(id)).thenReturn(Optional.of(video));

        Optional<Video> foundVideo = videoService.findVideoById(id);

        assertThat(foundVideo).isPresent();
        assertThat(foundVideo.get()).isEqualTo(video);
    }

    @Test
    void whenSaveVideo_thenShouldSaveAndReturnVideo() {
        when(videoRepository.save(video)).thenReturn(video);

        VideoDTO savedVideoDTO = videoService.saveVideo(videoDTO);

        assertThat(savedVideoDTO).isNotNull();
        verify(videoRepository, times(1)).save(video);
    }

    @Test
    void whenDeleteVideo_thenShouldInvokeDeletion() {
        Long id = 1L;
        doNothing().when(videoRepository).deleteById(id);

        videoService.deleteVideo(id);

        verify(videoRepository, times(1)).deleteById(id);
    }

    @Test
    void whenUpdateVideo_thenShouldUpdateAndReturnVideo() {
        Long id = 1L;
        videoDTO.setVideoID(id);
        when(videoRepository.findById(id)).thenReturn(Optional.of(video));
        when(videoRepository.save(video)).thenReturn(video);

        VideoDTO updatedVideoDTO = videoService.updateVideo(videoDTO);

        assertThat(updatedVideoDTO).isNotNull();
        verify(videoRepository, times(1)).save(video);
    }

    @Test
    void whenUpdateNonExistingVideo_thenThrowException() {
        Long id = 1L;
        videoDTO.setVideoID(id);
        when(videoRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> videoService.updateVideo(videoDTO))
                .isInstanceOf(VideoNotFoundException.class)
                .hasMessageContaining("Video not found with ID");

        verify(videoRepository, never()).save(any(Video.class));
    }
}
