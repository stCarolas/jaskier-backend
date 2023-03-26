package io.github.stcarolas.audd.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.stcarolas.audd.api.model.Stream;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

public class AudDClientTest {

    String url = "twitch:iamjustacrazy";

    @Test
    public void testAddingStream() {
        AudDClient client = new AudDClient("104095679257519b12958ea17d97c1b2");
        assertTrue(client.listStreams().isEmpty());

        client.addStream(1, url);
        List<Stream> streams = client.listStreams();
        assertTrue(streams.size() == 1);

        Stream stream = streams.head();
        assertEquals(1, stream.getRadioId());
        assertEquals(url, stream.getUrl());

        client.deleteStream(1);
        assertTrue(client.listStreams().isEmpty());
    }
}
