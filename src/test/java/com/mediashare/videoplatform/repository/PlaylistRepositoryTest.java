package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.model.Playlist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlaylistRepositoryTest extends AbstractPostgresContainerBaseTest {
    private @Autowired PlaylistRepository playlistRepository;

    @Test
    public void testPlaylistCreation() {
        Playlist playlist = new Playlist();
        playlist.setTitle("Test Playlist");

        Playlist savedPlaylist = playlistRepository.save(playlist);
        assertThat(savedPlaylist).isNotNull();
        assertThat(savedPlaylist.getPlaylistID()).isNotNull();
        assertThat(savedPlaylist.getTitle()).isEqualTo("Test Playlist");
    }

    @Test
    public void testPlaylistRetrieval() {
        Playlist playlist = new Playlist();
        playlist.setTitle("Test Playlist");
        Playlist savedPlaylist = playlistRepository.save(playlist);

        Optional<Playlist> foundPlaylist = playlistRepository.findById(savedPlaylist.getPlaylistID());
        assertThat(foundPlaylist).isPresent();
        assertThat(foundPlaylist.get().getTitle()).isEqualTo("Test Playlist");
    }

    @Test
    public void testPlaylistUpdate() {
        Playlist playlist = new Playlist();
        playlist.setTitle("Test Playlist");
        Playlist savedPlaylist = playlistRepository.save(playlist);

        savedPlaylist.setTitle("Updated Playlist");
        Playlist updatedPlaylist = playlistRepository.save(savedPlaylist);
        assertThat(updatedPlaylist.getTitle()).isEqualTo("Updated Playlist");
    }

    @Test
    public void testPlaylistDeletion() {
        Playlist playlist = new Playlist();
        playlist.setTitle("Test Playlist");
        Playlist savedPlaylist = playlistRepository.save(playlist);

        playlistRepository.delete(savedPlaylist);
        Optional<Playlist> deletedPlaylist = playlistRepository.findById(savedPlaylist.getPlaylistID());
        assertThat(deletedPlaylist).isEmpty();
    }
}