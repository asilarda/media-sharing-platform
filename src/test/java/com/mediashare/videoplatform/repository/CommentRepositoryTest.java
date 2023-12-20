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

    // postComment
    // retrieveComment
    // updateComment
    // deleteComment
    // commentspagination

    /*
    *
    *     Edge Cases: Test with null values, empty strings, very long strings, etc.
    Transactional Behavior: Verify that transactions work as expected (e.g., nothing is saved if an operation in a transaction fails).
    Concurrent Access: (Advanced) Test how the repository behaves under concurrent access, if applicable.
    *
    *
    * */
}