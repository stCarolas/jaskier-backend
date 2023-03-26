package io.github.stcarolas.audd.api.model;

import io.vavr.collection.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResponseWrapper<T> {
    private String status;
    private List<T> result;
}
