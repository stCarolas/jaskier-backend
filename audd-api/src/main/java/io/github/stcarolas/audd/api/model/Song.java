package io.github.stcarolas.audd.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Song {
    private String artist;
    private String title;
    private String album;
    @JsonProperty("song_link")
    private String songLink;
}
