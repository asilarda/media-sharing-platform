package com.mediashare.videoplatform.service;

import com.mediashare.videoplatform.dto.VideoDTO;
import com.mediashare.videoplatform.model.Video;
import com.mediashare.videoplatform.repository.VideoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    private final static Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Autowired
    public VideoService(VideoRepository videoRepository, ModelMapper modelMapper) {
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
    }

    public List<Video> findAllVideos() {
        return videoRepository.findAll();
    }

    public Optional<Video> findVideoById(Long id) {
        return videoRepository.findById(id);
    }

    public String deleteVideo(Long id) {
        videoRepository.deleteById(id);
        return "Video deleted with id" + id;
    }
    public VideoDTO saveVideo(VideoDTO videoDTO) {
        Video video = modelMapper.map(videoDTO, Video.class);
        logger.info("Mapped Video: {}", video);
        Video savedVideo = videoRepository.save(video);
        return modelMapper.map(savedVideo, VideoDTO.class);
    }

    public VideoDTO updateVideo(VideoDTO videoDTO) {
        if (videoDTO.getVideoID() == null) {
            throw new IllegalArgumentException("Video ID must not be null for update operation");
        }

        Video existingVideo = videoRepository
                .findById(videoDTO.getVideoID())
                .orElseThrow(() -> new IllegalArgumentException("Video with ID " + videoDTO.getVideoID() + " not found"));

        modelMapper.map(videoDTO, existingVideo);

        Video updatedVideo = videoRepository.save(existingVideo);
        return modelMapper.map(updatedVideo, VideoDTO.class);
    }
}
