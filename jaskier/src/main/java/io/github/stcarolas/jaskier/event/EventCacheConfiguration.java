package io.github.stcarolas.jaskier.event;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventCacheConfiguration {
    @Bean
    public Cache<String, Event> eventCache(DefaultCacheManager cacheManager) {
        cacheManager.defineConfiguration("event", new ConfigurationBuilder().build());
        return cacheManager.getCache("event");
    }
}
