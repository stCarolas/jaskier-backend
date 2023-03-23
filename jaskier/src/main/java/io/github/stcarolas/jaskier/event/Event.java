package io.github.stcarolas.jaskier.event;

import org.immutables.value.Value;

import io.github.stcarolas.jaskier.song.Song;
import io.vavr.collection.List;
import io.vavr.control.Either;

public abstract class Event {

    public abstract Either<List<Song>, Object> resultOrNotification();
    public abstract void save();

    @Value.Immutable
    public abstract static class EventImpl extends Event {
        public abstract EventStore store();
        public void save() {
            store().save(this);
        }
    }
}
