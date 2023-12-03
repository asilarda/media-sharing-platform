package com.mediashare.videoplatform.repository;

import com.mediashare.videoplatform.AbstractPostgresContainerBaseTest;
import com.mediashare.videoplatform.model.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ChannelRepositoryTest extends AbstractPostgresContainerBaseTest {
    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
    }

    private @Autowired ChannelRepository channelRepository;

    @Test
    public void testChannelCreation() {
        Channel channel = new Channel();
        Channel savedChannel = channelRepository.save(channel);
        assertThat(savedChannel).isNotNull();
        assertThat(savedChannel.getChannelID()).isNotNull();
    }

}