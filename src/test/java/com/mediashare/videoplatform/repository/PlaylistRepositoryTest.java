package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.model.Playlist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlaylistRepositoryTest extends AbstractPostgresContainerBaseTest {
    private @Autowired PlaylistRepository playlistRepository;

    @Test
    void testPlaylistCreation() {
        Playlist playlist = new Playlist();
        Playlist savedPlaylist = playlistRepository.save(playlist);
        assertThat(savedPlaylist).isNotNull();
    }

}