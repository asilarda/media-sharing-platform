package com.mediashare.videoplatform.repository.projections;

import java.time.LocalDateTime;

// Goal was to create a projection interface that would be used to list the results from a filter
public interface ListedVideo {
    long getVideoId();
    String getVideoTitle();
    LocalDateTime getUploadDate();
}
