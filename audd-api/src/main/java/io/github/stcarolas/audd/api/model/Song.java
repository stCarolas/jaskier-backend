package io.github.stcarolas.audd.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Song {
    private String artist;
    private String title;
    private String album;
    @JsonProperty("song_link")
    private String songLink;
}
