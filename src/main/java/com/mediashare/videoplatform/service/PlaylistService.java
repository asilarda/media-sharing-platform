package com.mediashare.videoplatform.service;

import com.mediashare.videoplatform.model.Playlist;
import com.mediashare.videoplatform.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mediashare.videoplatform.model.Rating;
import com.mediashare.videoplatform.repository.RatingRepository;

import java.util.List;
import java.util.Optional;
@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> findAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Optional<Playlist> findPlaylistById(Long id) {
        return playlistRepository.findById(id);
    }

    public Playlist savePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }
}
