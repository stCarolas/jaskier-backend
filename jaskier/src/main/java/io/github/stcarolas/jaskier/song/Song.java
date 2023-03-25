package io.github.stcarolas.jaskier.song;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSong.class)
@JsonDeserialize(as = ImmutableSong.class)
public abstract class Song {

    public abstract String artist();
    public abstract String title();
    public abstract String songLink();

}
