package com.mediashare.videoplatform.controller;

import com.mediashare.videoplatform.dto.VideoDTO;
import com.mediashare.videoplatform.model.Video;
import com.mediashare.videoplatform.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("videos")
public class VideoController {
    @Autowired
    private VideoService videoService;
    private final static Logger logger = LoggerFactory.getLogger(VideoController.class);

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody Optional<Video> getVideo(@PathVariable Long id) {
        return videoService.findVideoById(id);
    }

    @PostMapping
    public @ResponseBody VideoDTO createVideo(@RequestBody VideoDTO video) {
        return videoService.saveVideo(video);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteVideo(@PathVariable Long id) {
        return videoService.deleteVideo(id);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody VideoDTO updateVideo(@PathVariable Long id, @RequestBody VideoDTO video) {
        video.setVideoID(id);
        return videoService.updateVideo(video);
    }
}
