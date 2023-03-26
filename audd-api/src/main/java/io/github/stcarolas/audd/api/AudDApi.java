package io.github.stcarolas.audd.api;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.stcarolas.audd.api.model.ResponseWrapper;
import io.github.stcarolas.audd.api.model.Stream;

public interface AudDApi {
    @RequestLine("POST /addStream")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public void addStream(
        @Param("api_token") String apiToken,
        @Param("radio_id") Integer radioId,
        @Param("url") String url,
        @Param("callbacks") String callbacks
    );

    @RequestLine("POST /setStreamUrl")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public void setStreamUrl(
        @Param("api_token") String apiToken,
        @Param("radio_id") Integer radioId,
        @Param("url") String url
    );

    @RequestLine("POST /deleteStream")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public void deleteStream(
        @Param("api_token") String apiToken,
        @Param("radio_id") Integer radioId
    );

    @RequestLine("POST /getStreams")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public ResponseWrapper<Stream> getStreams(
        @Param("api_token") String apiToken
    );
}
