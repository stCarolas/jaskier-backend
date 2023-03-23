package io.github.stcarolas.audd.api.model;

import io.vavr.collection.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecognitionResult {
    private String radioId;
    private List<Song> results;
}
