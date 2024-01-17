package com.mediashare.videoplatform.controller;


import com.mediashare.videoplatform.dto.PlaylistDTO;
import com.mediashare.videoplatform.model.Playlist;
import com.mediashare.videoplatform.service.PlaylistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("playlists")
public class PlaylistController {
    private final static Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private PlaylistService playlistService;


    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable Long id) {
        logger.debug("GET request for playlist with ID: {}", id);
        try {
            Playlist playlist = playlistService.findPlaylistById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Playlist not found with ID: " + id));
            return ResponseEntity.ok(playlist);
        } catch (Exception e) {
            logger.error("Failed to get playlist with ID: {}", id, e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        logger.debug("POST request to create a playlist");
        try {
            PlaylistDTO createdPlaylist = playlistService.savePlaylist(playlistDTO);
            logger.info("Created a new playlist with ID: {}", createdPlaylist.getPlaylistID());
            return ResponseEntity.ok(createdPlaylist);
        } catch (Exception e) {
            logger.error("Failed to create playlist", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long id) {
        logger.debug("DELETE request for playlist with ID: {}", id);
        try {
            playlistService.deletePlaylist(id);
            logger.info("Deleted playlist with ID: {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete playlist with ID: {}", id, e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistDTO> updatePlaylist(@PathVariable Long id, @RequestBody PlaylistDTO playlistDTO) {
        logger.debug("PUT request to update playlist with ID: {}", id);
        try {
            playlistDTO.setPlaylistID(id);
            PlaylistDTO updatedPlaylist = playlistService.updatePlaylist(playlistDTO);
            logger.info("Updated playlist with ID: {}", updatedPlaylist.getPlaylistID());
            return ResponseEntity.ok(updatedPlaylist);
        } catch (Exception e) {
            logger.error("Failed to update playlist with ID: {}", id, e);
            throw e;
        }
    }
}
