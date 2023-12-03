package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
