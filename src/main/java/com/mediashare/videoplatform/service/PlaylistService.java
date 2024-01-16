package com.mediashare.videoplatform.service;

import com.mediashare.videoplatform.model.Playlist;
import com.mediashare.videoplatform.repository.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final static Logger logger = LoggerFactory.getLogger(PlaylistService.class);

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Transactional(readOnly = true)
    public List<Playlist> findAllPlaylists() {
        logger.info("Fetching all playlists");
        return playlistRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Playlist> findPlaylistById(Long id) {
        logger.info("Fetching playlist with ID: {}", id);
        return playlistRepository.findById(id);
    }

    @Transactional
    public Playlist savePlaylist(Playlist playlist) {
        logger.info("Saving new playlist");
        return playlistRepository.save(playlist);
    }


    @Transactional
    public void deletePlaylist(Long id) {
        logger.info("Removing playlist with ID: {}", id);
        playlistRepository.deleteById(id);
    }
}
