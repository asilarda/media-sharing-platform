package com.mediashare.videoplatform.controller;

import com.mediashare.videoplatform.dto.CommentDTO;
import com.mediashare.videoplatform.model.Comment;
import com.mediashare.videoplatform.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        logger.debug("GET request for comment with ID: {}", id);
        try {
            Comment comment = commentService.findCommentById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + id));
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            logger.error("Failed to get comment with ID: {}", id, e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        logger.debug("POST request to create a comment");
        try {
            CommentDTO createdComment = commentService.saveComment(commentDTO);
            logger.info("Created a new comment with ID: {}", createdComment.getCommentID());
            return ResponseEntity.ok(createdComment);
        } catch (Exception e) {
            logger.error("Failed to create comment", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        logger.debug("DELETE request for comment with ID: {}", id);
        try {
            commentService.deleteComment(id);
            logger.info("Deleted comment with ID: {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete comment with ID: {}", id, e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        logger.debug("PUT request to update comment with ID: {}", id);
        try {
            commentDTO.setCommentID(id);
            CommentDTO updatedComment = commentService.updateComment(commentDTO);
            logger.info("Updated comment with ID: {}", updatedComment.getCommentID());
            return ResponseEntity.ok(updatedComment);
        } catch (Exception e) {
            logger.error("Failed to update comment with ID: {}", id, e);
            throw e;
        }
    }
}
