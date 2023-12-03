package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
