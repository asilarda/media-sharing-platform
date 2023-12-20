package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest extends AbstractPostgresContainerBaseTest {

    private @Autowired UserRepository userRepository;

    @Test
    public void shouldSaveUser() {
        User user = new User();
        User savedUser = userRepository.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUserID()).isNotNull();
    }

    // createUser
    // findUserbyID
    // updateUser
    // deleteUser
    // findUserByUsername
    // listAllUsers
    // usernameExistenceCheck
    // emailExistenceCheck


    /*
    *
    *     Edge Cases: Test with null values, empty strings, very long strings, etc.
    Transactional Behavior: Verify that transactions work as expected (e.g., nothing is saved if an operation in a transaction fails).
    Concurrent Access: (Advanced) Test how the repository behaves under concurrent access, if applicable.
    *
    *
    * */
}
