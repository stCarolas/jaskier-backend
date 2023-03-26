package io.github.stcarolas.audd.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import io.github.stcarolas.audd.api.model.Stream;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.jackson.datatype.VavrModule;

public class AudDClient {

    private AudDApi api;
    private String token;

    public AudDClient(String apiToken) {
        this.token = apiToken;
        ObjectMapper mapper = mapper();
        api =
            Feign
                .builder()
                .encoder(new FormEncoder(new JacksonEncoder(mapper)))
                .decoder(new JacksonDecoder(mapper))
                .target(AudDApi.class, "https://api.audd.io");
    }

    private ObjectMapper mapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new VavrModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    public void addStream(Integer id, String url) {
        api.addStream(token, id, url, "before");
    }

    public void setStreamUrl(Integer id, String url) {
        api.setStreamUrl(token, id, url);
    }

    public void deleteStream(Integer id) {
        api.deleteStream(token, id);
    }

    public List<Stream> listStreams() {
        return Option
            .of(api.getStreams(token).getResult())
            .map(List::ofAll)
            .getOrElse(List.empty());
    }
}
