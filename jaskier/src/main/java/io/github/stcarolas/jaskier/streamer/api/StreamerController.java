package io.github.stcarolas.jaskier.streamer.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.stcarolas.audd.api.AudDClient;
import io.github.stcarolas.jaskier.streamer.StreamerImplCriteria;
import io.github.stcarolas.jaskier.streamer.StreamerImplRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/streamers")
@RequiredArgsConstructor
public class StreamerController {

    private final StreamerImplRepository streamerRepository;
    private final AudDClient audDClient;

    @PostMapping
    public void addStreamer(@RequestBody NewStreamerRequest newStreamerRequest) {
        newStreamerRequest.execute(audDClient).save(streamerRepository);
    }

    @DeleteMapping("{id}")
    public void deleteStreamer(@PathVariable Integer id) {
        streamerRepository.delete(StreamerImplCriteria.streamerImpl.id.is(id));
    }

}
