package io.github.stcarolas.audd.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Stream {
    @JsonProperty("radio_id")
    private Integer radioId;

    private String url;

    @JsonProperty("stream_running")
    private boolean streamRunning;

    @JsonProperty("longpoll_category")
    private String longpollCategory;
}
