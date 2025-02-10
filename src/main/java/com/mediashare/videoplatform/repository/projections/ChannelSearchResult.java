package com.mediashare.videoplatform.repository.projections;

// Goal was to create a projection interface that would be used to list the search results for a channel
public interface ChannelSearchResult {
    long getChannelId();
    String getChannelName();
    String getChannelImageUrl();
}
