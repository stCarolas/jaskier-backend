package io.github.stcarolas.jaskier.streamer;

import org.immutables.criteria.backend.Backend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StreamerConfiguration {

    @Bean
    public StreamerImplRepository streamerImplRepository(Backend backend) {
        return new StreamerImplRepository(backend);
    }
}
