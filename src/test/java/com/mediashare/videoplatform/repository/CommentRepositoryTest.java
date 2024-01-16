package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommentRepositoryTest extends AbstractPostgresContainerBaseTest {
    private @Autowired CommentRepository commentRepository;

    @Test
    public void testCommentCreation() {
        Comment comment = new Comment();
        comment.setText("Test Comment");

        Comment savedComment = commentRepository.save(comment);
        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getCommentID()).isNotNull();
        assertThat(savedComment.getText()).isEqualTo("Test Comment");
    }

    @Test
    public void testCommentRetrieval() {
        Comment comment = new Comment();
        comment.setText("Test Comment");
        Comment savedComment = commentRepository.save(comment);

        Optional<Comment> foundComment = commentRepository.findById(savedComment.getCommentID());
        assertThat(foundComment).isPresent();
        assertThat(foundComment.get().getText()).isEqualTo("Test Comment");
    }

    @Test
    public void testCommentUpdate() {
        Comment comment = new Comment();
        comment.setText("Test Comment");
        Comment savedComment = commentRepository.save(comment);

        savedComment.setText("Updated Comment");
        Comment updatedComment = commentRepository.save(savedComment);
        assertThat(updatedComment.getText()).isEqualTo("Updated Comment");
    }

    @Test
    public void testCommentDeletion() {
        Comment comment = new Comment();
        comment.setText("Test Comment");
        Comment savedComment = commentRepository.save(comment);

        commentRepository.delete(savedComment);
        Optional<Comment> deletedComment = commentRepository.findById(savedComment.getCommentID());
        assertThat(deletedComment).isEmpty();
    }
}