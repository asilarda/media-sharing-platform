package com.mediashare.videoplatform.service;

import com.mediashare.videoplatform.model.Comment;
import com.mediashare.videoplatform.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional(readOnly = true)
    public List<Comment> findAllComments() {
        logger.info("Fetching all comments");
        return commentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findCommentById(Long id) {
        logger.info("Fetching comment with ID: {}", id);
        return commentRepository.findById(id);
    }

    @Transactional
    public Comment saveComment(Comment comment) {
        logger.info("Saving new comment");
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        logger.info("Deleting comment with ID: {}", id);
        commentRepository.deleteById(id);
    }
}
