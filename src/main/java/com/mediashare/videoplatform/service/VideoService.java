package com.mediashare.videoplatform.service;

import com.mediashare.videoplatform.dto.VideoDTO;
import com.mediashare.videoplatform.exception.VideoNotFoundException;
import com.mediashare.videoplatform.model.Video;
import com.mediashare.videoplatform.repository.VideoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public List<Video> findAllVideos() {
        logger.debug("Retrieving all videos");
        return videoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Video> findVideoById(Long id) {
        logger.debug("Retrieving video with ID: {}", id);
        return videoRepository.findById(id);
    }

    @Transactional
    public void deleteVideo(Long id) {
        logger.debug("Deleting video with ID: {}", id);
        videoRepository.deleteById(id);
        logger.info("Video deleted with ID: {}", id);
    }

    @Transactional
    public VideoDTO saveVideo(VideoDTO videoDTO) {
        logger.debug("Saving new video");
        try {
            Video video = modelMapper.map(videoDTO, Video.class);
            Video savedVideo = videoRepository.save(video);
            logger.info("Video saved with ID: {}", savedVideo.getVideoID());
            return modelMapper.map(savedVideo, VideoDTO.class);
        } catch (Exception e) {
            logger.error("Error saving video: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public VideoDTO updateVideo(VideoDTO videoDTO) {
        logger.debug("Updating video with ID: {}", videoDTO.getVideoID());
        if (videoDTO.getVideoID() == null) {
            logger.error("Video ID must not be null for update operation");
            throw new IllegalArgumentException("Video ID must not be null for update operation");
        }
        try {
            Video existingVideo = videoRepository.findById(videoDTO.getVideoID())
                    .orElseThrow(() -> new VideoNotFoundException("Video with ID " + videoDTO.getVideoID() + " not found"));
            modelMapper.map(videoDTO, existingVideo);
            Video updatedVideo = videoRepository.save(existingVideo);
            logger.info("Video updated with ID: {}", updatedVideo.getVideoID());
            return modelMapper.map(updatedVideo, VideoDTO.class);
        } catch (VideoNotFoundException e) {
            logger.error("Error updating video: Video not found", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error updating video", e);
            throw e;
        }
    }
}
