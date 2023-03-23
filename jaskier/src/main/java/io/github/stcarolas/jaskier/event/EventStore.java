package io.github.stcarolas.jaskier.event;

import java.util.UUID;

import org.infinispan.Cache;
import org.springframework.stereotype.Service;

import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventStore {

    private final Cache<String, Event> cache;

    public List<Event> list(){
        return List.ofAll(cache.values());
    }

    public void save(Event event){
        cache.put(UUID.randomUUID().toString(), event);
    }

}
