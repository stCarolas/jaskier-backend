package io.github.stcarolas.jaskier.song;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableSong.class)
@JsonDeserialize(as = ImmutableSong.class)
public abstract class Song {

    public abstract String artist();

    public abstract String title();

    public abstract String songLink();
}
