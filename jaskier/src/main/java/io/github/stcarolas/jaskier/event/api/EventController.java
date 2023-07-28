package io.github.stcarolas.jaskier.event.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.stcarolas.audd.api.model.CallbackEvent;
import io.github.stcarolas.audd.api.model.RecognitionResult;
import io.github.stcarolas.jaskier.event.Event;
import io.github.stcarolas.jaskier.event.EventImplCriteria;
import io.github.stcarolas.jaskier.event.EventImplRepository;
import io.github.stcarolas.jaskier.event.ImmutableEventImpl;
import io.github.stcarolas.jaskier.song.ImmutableSong;
import io.github.stcarolas.jaskier.song.Song;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Log4j2
public class EventController {

    private final EventImplRepository eventRepository;

    @PostMapping
    public void callback(@RequestBody CallbackEvent event) {
        log.info("Got event: {}", event);
        RecognitionResult result = event.getResult().get();
        List<Song> songs = result
            .getResults()
            .map(song ->
                ImmutableSong
                    .builder()
                    .artist(song.getArtist())
                    .title(song.getTitle())
                    .songLink(song.getSongLink())
                    .build()
            );
        ImmutableEventImpl
            .builder()
            .channelId(result.getRadioId())
            .timestamp(result.getTimestamp())
            .resultOrNotification(Either.left(songs))
            .build()
            .save(eventRepository);
    }

    @GetMapping
    public List<CallbackEvent> list(
        @RequestParam(name = "channelId", required = true) String channelId
    ) {
        List<Event> events = List.ofAll(
            eventRepository
                .find(EventImplCriteria.eventImpl.channelId.is(channelId))
                .orderBy(EventImplCriteria.eventImpl.timestamp.desc())
                .limit(20)
                .fetch()
        );
        return events.map(event -> {
            var result = new RecognitionResult();
            var callbackEvent = new CallbackEvent();
            result.setTimestamp(event.timestamp());
            result.setResults(
                event
                    .resultOrNotification()
                    .getLeft()
                    .map(song -> {
                        var apiSong =
                            new io.github.stcarolas.audd.api.model.Song();
                        apiSong.setArtist(song.artist());
                        apiSong.setTitle(song.title());
                        apiSong.setSongLink(song.songLink());
                        return apiSong;
                    })
            );
            callbackEvent.setResult(Option.of(result));
            return callbackEvent;
        });
    }
}
