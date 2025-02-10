package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.model.Channel;
import com.mediashare.videoplatform.repository.projections.ChannelSearchResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    // A bit unsure here
    Collection<ChannelSearchResult> findByName(String name);
}
