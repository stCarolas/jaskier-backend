package io.github.stcarolas.jaskier.event;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.stcarolas.audd.api.model.CallbackEvent;
import io.github.stcarolas.audd.api.model.RecognitionResult;
import io.github.stcarolas.jaskier.song.Song;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsController {

    private final EventStore eventStore;

    @PostMapping
    public void callback(@RequestBody CallbackEvent event) {
        List<Song> songs = event.getResult().get().getResults()
            .map(result -> new Song(result.getArtist(), result.getTitle(), result.getSongLink()));
        ImmutableEventImpl.builder()
            .resultOrNotification(Either.left(songs))
            .store(eventStore)
            .build()
            .save();
    }

    @GetMapping
    public List<CallbackEvent> list() {
        return eventStore.list().map(event -> {
            var result = new RecognitionResult();
            var callbackEvent = new CallbackEvent();
            result.setResults(
                event.resultOrNotification().getLeft()
                    .map(song -> {
                        var apiSong = new io.github.stcarolas.audd.api.model.Song();
                        apiSong.setArtist(song.getArtist());
                        apiSong.setTitle(song.getTitle());
                        apiSong.setSongLink(song.getSongLink());
                        return apiSong;
                    })
            );
            callbackEvent.setResult(Option.of(result));
            return callbackEvent;
        }
        );
    }
}
