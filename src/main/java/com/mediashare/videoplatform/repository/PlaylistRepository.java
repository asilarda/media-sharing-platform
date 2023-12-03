package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
