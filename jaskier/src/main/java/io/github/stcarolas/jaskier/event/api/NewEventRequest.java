package io.github.stcarolas.jaskier.event.api;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.stcarolas.jaskier.event.Event;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableNewEventRequest.class)
@JsonDeserialize(as = ImmutableNewEventRequest.class)
public abstract class NewEventRequest extends Event {

    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd hh:mm:ss"
    )
    public abstract Date timestamp();

}
