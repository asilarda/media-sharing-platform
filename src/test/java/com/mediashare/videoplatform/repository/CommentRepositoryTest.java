package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommentRepositoryTest extends AbstractPostgresContainerBaseTest {
    private @Autowired CommentRepository commentRepository;

    @Test
    void testCommentCreation() {
        Comment comment = new Comment();
        Comment savedComment = commentRepository.save(comment);
        assertThat(savedComment).isNotNull();
    }
}