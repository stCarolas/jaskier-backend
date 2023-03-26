package io.github.stcarolas.jaskier.event;

import org.immutables.criteria.backend.Backend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfiguration {

    @Bean
    public EventRepository eventRepository(Backend backend) {
        return new EventRepository(backend);
    }

}
