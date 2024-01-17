package com.mediashare.videoplatform.service;

import com.mediashare.videoplatform.dto.PlaylistDTO;
import com.mediashare.videoplatform.model.Playlist;
import com.mediashare.videoplatform.repository.PlaylistRepository;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    private final static Logger logger = LoggerFactory.getLogger(PlaylistService.class);

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, ModelMapper modelMapper) {
        this.playlistRepository = playlistRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<Playlist> findAllPlaylists() {
        logger.debug("Retrieving all playlists");
        return playlistRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Playlist> findPlaylistById(Long id) {
        logger.debug("Retrieving playlist with ID: {}", id);
        return playlistRepository.findById(id);
    }

    @Transactional
    public void deletePlaylist(Long id) {
        logger.debug("Deleting playlist with ID: {}", id);
        playlistRepository.deleteById(id);
        logger.info("Playlist deleted with ID: {}", id);
    }

    @Transactional
    public PlaylistDTO savePlaylist(PlaylistDTO playlistDTO) {
        logger.debug("Saving new playlist");
        try {
            Playlist playlist = modelMapper.map(playlistDTO, Playlist.class);
            Playlist savedPlaylist = playlistRepository.save(playlist);
            logger.info("Playlist saved with ID: {}", savedPlaylist.getPlaylistID());
            return modelMapper.map(savedPlaylist, PlaylistDTO.class);
        } catch (Exception e) {
            logger.error("Error saving playlist: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public PlaylistDTO updatePlaylist(PlaylistDTO playlistDTO) {
        logger.debug("Updating playlist with ID: {}", playlistDTO.getPlaylistID());
        if (playlistDTO.getPlaylistID() == null) {
            logger.error("Playlist ID must not be null for update operation");
            throw new IllegalArgumentException("Playlist ID must not be null for update operation");
        }
        try {
            Playlist existingPlaylist = playlistRepository.findById(playlistDTO.getPlaylistID())
                    .orElseThrow(() -> new IllegalArgumentException("Playlist with ID " + playlistDTO.getPlaylistID() + " not found"));
            modelMapper.map(playlistDTO, existingPlaylist);
            Playlist updatedPlaylist = playlistRepository.save(existingPlaylist);
            logger.info("Playlist updated with ID: {}", updatedPlaylist.getPlaylistID());
            return modelMapper.map(updatedPlaylist, PlaylistDTO.class);
        } catch (IllegalArgumentException e) {
            logger.error("Error updating playlist: Playlist not found", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error updating playlist", e);
            throw e;
        }
    }
}
