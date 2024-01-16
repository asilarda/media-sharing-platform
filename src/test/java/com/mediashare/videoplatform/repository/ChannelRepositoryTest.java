package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.model.Channel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ChannelRepositoryTest extends AbstractPostgresContainerBaseTest {
    private @Autowired ChannelRepository channelRepository;

    private Channel testChannel;

    @BeforeEach
    void setUp() {
        testChannel = new Channel();
        testChannel.setName("Test Channel");
        testChannel.setDescription("A description");
    }

    @AfterEach
    void tearDown() {
        channelRepository.deleteAll();
    }

    @Test
    public void testChannelCreation() {
        Channel savedChannel = channelRepository.save(testChannel);
        assertThat(savedChannel).isNotNull();
        assertThat(savedChannel.getChannelID()).isNotNull();
        assertThat(savedChannel.getName()).isEqualTo("Test Channel");
    }

    @Test
    public void testChannelRetrieval() {
        Channel savedChannel = channelRepository.save(testChannel);

        Optional<Channel> foundChannel = channelRepository.findById(savedChannel.getChannelID());
        assertThat(foundChannel).isPresent();
        assertThat(foundChannel.get().getName()).isEqualTo("Test Channel");
    }

    @Test
    public void testChannelUpdate() {
        Channel savedChannel = channelRepository.save(testChannel);

        savedChannel.setName("Updated Channel");
        Channel updatedChannel = channelRepository.save(savedChannel);
        assertThat(updatedChannel.getName()).isEqualTo("Updated Channel");
    }

    @Test
    public void testChannelDeletion() {
        Channel savedChannel = channelRepository.save(testChannel);

        channelRepository.delete(savedChannel);
        Optional<Channel> deletedChannel = channelRepository.findById(savedChannel.getChannelID());
        assertThat(deletedChannel).isEmpty();
    }
}