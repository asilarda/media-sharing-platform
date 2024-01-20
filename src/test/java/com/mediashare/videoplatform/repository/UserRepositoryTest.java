package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest extends AbstractPostgresContainerBaseTest {

    private @Autowired UserRepository userRepository;

    @Test
    public void testUserCreation() {
        User user = new User();
        user.setUsername("TestUser");
        user.setEmail("max@muster.at");

        User savedUser = userRepository.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUserID()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("TestUser");
        assertThat(savedUser.getEmail()).isEqualTo("max@muster.at");
    }
    @Test
    public void testUserRetrieval() {
        User user = new User();
        user.setUsername("Test User");
        user.setEmail("max@muster.at");
        User savedUser = userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(savedUser.getUserID());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("Test User");
        assertThat(savedUser.getEmail()).isEqualTo("max@muster.at");
    }

    @Test
    public void testUserUpdate() {
        User user = new User();
        user.setUsername("Test User");
        user.setEmail("max@muster.at");
        User savedUser = userRepository.save(user);

        savedUser.setUsername("Updated User");
        savedUser.setEmail("maxupdated@muster.at");
        User updatedUser = userRepository.save(savedUser);
        assertThat(updatedUser.getUsername()).isEqualTo("Updated User");
        assertThat(updatedUser.getEmail()).isEqualTo("maxupdated@muster.at");
    }

    @Test
    public void testUserDeletion() {
        User user = new User();
        user.setUsername("Test User");
        user.setEmail("maxupdated@muster.at");
        User savedUser = userRepository.save(user);

        userRepository.delete(savedUser);
        Optional<User> deletedUser = userRepository.findById(savedUser.getUserID());
        assertThat(deletedUser).isEmpty();
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
