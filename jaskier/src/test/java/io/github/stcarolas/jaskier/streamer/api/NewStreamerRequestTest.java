package io.github.stcarolas.jaskier.streamer.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import io.github.stcarolas.audd.api.AudDClient;
import io.github.stcarolas.audd.api.model.Stream;
import io.github.stcarolas.jaskier.streamer.ImmutableStreamerImpl;
import io.github.stcarolas.jaskier.streamer.StreamerImpl;
import io.vavr.collection.List;

public class NewStreamerRequestTest {
    String streamUrl = RandomStringUtils.randomAlphabetic(10);
    String auddKey = RandomStringUtils.randomAlphabetic(10);
    String twitchId = RandomStringUtils.randomAlphabetic(10);

    @Test
    public void testExecutingRequest() {
        AudDClient audDClient = mock(AudDClient.class);
        when(audDClient.listStreams()).thenReturn(List.of(new Stream()));
        ImmutableStreamerImpl expectedCreatedStreamer = ImmutableStreamerImpl.builder()
            .id(1)
            .url(streamUrl)
            .auddKey(auddKey)
            .twitchId(twitchId)
            .build();
        StreamerImpl createdStreamer = ImmutableNewStreamerRequest.builder()
            .url(streamUrl)
            .auddKey(auddKey)
            .twitchId(twitchId)
            .build()
            .execute(audDClient);
        verify(audDClient).addStream(2,streamUrl);
        assertEquals(expectedCreatedStreamer, createdStreamer);
    }

}
