package io.github.stcarolas.jaskier.streamer.api;

import org.immutables.value.Value;

import io.github.stcarolas.audd.api.AudDClient;
import io.github.stcarolas.jaskier.streamer.ImmutableStreamerImpl;
import io.github.stcarolas.jaskier.streamer.Streamer;
import io.github.stcarolas.jaskier.streamer.StreamerImpl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.NonNull;

@Value.Immutable
@JsonSerialize(as = ImmutableNewStreamerRequest.class)
@JsonDeserialize(as = ImmutableNewStreamerRequest.class)
public abstract class NewStreamerRequest extends Streamer {

    public StreamerImpl execute(@NonNull AudDClient audDClient) {
        Integer id;
        synchronized (audDClient) {
            id = audDClient.listStreams().size() + 1;
            audDClient.addStream(id, this.url());
        }
        return ImmutableStreamerImpl.builder().id(id).from(this).build();
    }
}
