package io.github.stcarolas.audd.api.model;

import io.vavr.control.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CallbackEvent {
    private String status;
    private Option<RecognitionResult> result;
}
