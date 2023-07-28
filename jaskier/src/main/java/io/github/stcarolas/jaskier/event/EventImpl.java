package io.github.stcarolas.jaskier.event;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.criteria.Criteria;
import org.immutables.criteria.repository.sync.SyncRepository;
import org.immutables.value.Value;

@Value.Immutable
@Criteria
@Criteria.Repository
@JsonSerialize(as = ImmutableEventImpl.class)
@JsonDeserialize(as = ImmutableEventImpl.class)
public abstract class EventImpl extends Event {

    @Criteria.Id
    @Value.Default
    public String id() {
        return UUID.randomUUID().toString();
    }

    public void save(SyncRepository.Writable<EventImpl> repository) {
        repository.upsert(this);
    }

}
