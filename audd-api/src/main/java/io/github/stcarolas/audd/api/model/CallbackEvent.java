package io.github.stcarolas.audd.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.vavr.control.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallbackEvent {
    private String status;
    private Option<RecognitionResult> result;
}
