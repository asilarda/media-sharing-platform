package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mediashare.videoplatform.repository.projections.ListedVideo;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    // same as for the channel
    Collection<ListedVideo> findByTitleContaining(String title);

    // not sure whether this is the correct way to do it
    @Query("SELECT v FROM Video v WHERE v.title LIKE %:title% AND v.uploadDate >= :date")
    Collection<ListedVideo> findByTitleAndUploadDateAfter(@Param("title") String title, @Param("date") LocalDateTime date);
}
