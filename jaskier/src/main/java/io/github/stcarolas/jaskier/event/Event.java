package io.github.stcarolas.jaskier.event;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.criteria.Criteria;
import org.immutables.criteria.repository.sync.SyncRepository;
import org.immutables.value.Value;

import io.github.stcarolas.jaskier.song.Song;
import io.vavr.collection.List;
import io.vavr.control.Either;

@Value.Immutable
@Criteria
@Criteria.Repository
@JsonSerialize(as = ImmutableEvent.class)
@JsonDeserialize(as = ImmutableEvent.class)
public abstract class Event {

    @Criteria.Id
    @Value.Default
    public String id() {
        return UUID.randomUUID().toString();
    }

    public abstract String channelId();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public abstract Date timestamp();

    public abstract Either<List<Song>, Object> resultOrNotification();

    public void save(SyncRepository.Writable<Event> repository) {
        repository.upsert(this);
    }

}
