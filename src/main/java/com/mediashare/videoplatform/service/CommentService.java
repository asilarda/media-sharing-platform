package com.mediashare.videoplatform.service;

import com.mediashare.videoplatform.dto.CommentDTO;
import com.mediashare.videoplatform.model.Comment;
import com.mediashare.videoplatform.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final ModelMapper modelMapper;

    private final static Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<Comment> findAllComments() {
        logger.debug("Retrieving all comments");
        return commentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findCommentById(Long id) {
        logger.debug("Retrieving comment with ID: {}", id);
        return commentRepository.findById(id);
    }

    @Transactional
    public void deleteComment(Long id) {
        logger.debug("Deleting comment with ID: {}", id);
        commentRepository.deleteById(id);
        logger.info("Comment deleted with ID: {}", id);
    }

    @Transactional
    public CommentDTO saveComment(CommentDTO commentDTO) {
        logger.debug("Saving new comment");
        try {
            Comment comment = modelMapper.map(commentDTO, Comment.class);
            Comment savedComment = commentRepository.save(comment);
            logger.info("Comment saved with ID: {}", savedComment.getCommentID());
            return modelMapper.map(savedComment, CommentDTO.class);
        } catch (Exception e) {
            logger.error("Error saving comment: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public CommentDTO updateComment(CommentDTO commentDTO) {
        logger.debug("Updating comment with ID: {}", commentDTO.getCommentID());
        if (commentDTO.getCommentID() == null) {
            logger.error("Comment ID must not be null for update operation");
            throw new IllegalArgumentException("Comment ID must not be null for update operation");
        }
        try {
            Comment existingComment = commentRepository.findById(commentDTO.getCommentID())
                    .orElseThrow(() -> new IllegalArgumentException("Comment with ID " + commentDTO.getCommentID() + " not found"));
            modelMapper.map(commentDTO, existingComment);
            Comment updatedComment = commentRepository.save(existingComment);
            logger.info("Comment updated with ID: {}", updatedComment.getCommentID());
            return modelMapper.map(updatedComment, CommentDTO.class);
        } catch (IllegalArgumentException e) {
            logger.error("Error updating comment: Comment not found", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error updating comment", e);
            throw e;
        }
    }
}
