package io.github.stcarolas.jaskier.event;

import java.util.Date;

import io.github.stcarolas.jaskier.song.Song;
import io.vavr.collection.List;
import io.vavr.control.Either;

public abstract class Event {

    public abstract String channelId();

    public abstract Date timestamp();

    public abstract Either<List<Song>, Object> resultOrNotification();
}
