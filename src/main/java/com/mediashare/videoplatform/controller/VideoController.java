package com.mediashare.videoplatform.controller;

import com.mediashare.videoplatform.dto.VideoDTO;
import com.mediashare.videoplatform.exception.VideoNotFoundException;
import com.mediashare.videoplatform.model.Video;
import com.mediashare.videoplatform.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("videos")
public class VideoController {

    private final VideoService videoService;
    private final static Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideo(@PathVariable Long id) {
        logger.debug("GET request for video with ID: {}", id);
        try {
            Video video = videoService.findVideoById(id)
                    .orElseThrow(() -> new VideoNotFoundException("Video not found with ID: " + id));
            return ResponseEntity.ok(video);
        } catch (Exception e) {
            logger.error("Failed to get video with ID: {}", id, e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<VideoDTO> createVideo(@RequestBody VideoDTO videoDTO) {
        logger.debug("POST request to create a video");
        try {
            VideoDTO createdVideo = videoService.saveVideo(videoDTO);
            logger.info("Created a new video with ID: {}", createdVideo.getVideoID());
            return ResponseEntity.ok(createdVideo);
        } catch (Exception e) {
            logger.error("Failed to create video", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id) {
        logger.debug("DELETE request for video with ID: {}", id);
        try {
            videoService.deleteVideo(id);
            logger.info("Deleted video with ID: {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete video with ID: {}", id, e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDTO> updateVideo(@PathVariable Long id, @RequestBody VideoDTO videoDTO) {
        logger.debug("PUT request to update video with ID: {}", id);
        try {
            videoDTO.setVideoID(id);
            VideoDTO updatedVideo = videoService.updateVideo(videoDTO);
            logger.info("Updated video with ID: {}", updatedVideo.getVideoID());
            return ResponseEntity.ok(updatedVideo);
        } catch (Exception e) {
            logger.error("Failed to update video with ID: {}", id, e);
            throw e;
        }
    }
}
