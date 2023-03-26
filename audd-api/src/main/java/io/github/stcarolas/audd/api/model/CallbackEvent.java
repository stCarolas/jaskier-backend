package io.github.stcarolas.audd.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.vavr.control.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class CallbackEvent {
    private String status;
    private Option<RecognitionResult> result;
}
