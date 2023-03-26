package io.github.stcarolas.jaskier;

import java.net.URI;
import java.util.Collections;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SocketTest {

    @Test
    public void testSelenium() {
        // WebDriverManager.chromedriver().setup();
        // WebDriver driver = new ChromeDriver();
        // driver.get("");
        // driver.findEle
    }

    @Test
    @SneakyThrows
    @Disabled
    public void testInternalApi() {
        URI uri = URI.create("wss://socket3.donationalerts.ru:443");
        IO.Options options = IO.Options.builder()
                .build();

        Socket socket = IO.socket(uri, options);
        socket.io().on(Manager.EVENT_OPEN, args -> {
            log.info("open");
        });
        socket.io().on(Manager.EVENT_RECONNECT_ATTEMPT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                log.info("reconnect attempt");
            }
        });
        socket.onAnyIncoming(new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                log.info("onAnyIncoming: {}", args);
            }
        });
        socket.connect().emit("add-user","{\"token\": \"77axuDCokJFmWsfvP4PP\", \"type\": 'minor'}");
        Thread.sleep(2000000);

    }

    @Test
    @SneakyThrows
    @Disabled
    public void testConection() {
        WebSocketClient socket = new StandardWebSocketClient();
        WebSocketSession session = socket.execute(new WebSocketHandler() {

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                log.info("afterConnectionClosed");
            }

            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                log.info("afterConnectionEstablished");
            }

            @Override
            public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
                log.info("handleMessage: {}", message);
                
            }

            @Override
            public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
                log.error("handleTransportError", exception);
            }

            @Override
            public boolean supportsPartialMessages() {
                return false;
            }

}, "wss://centrifugo.donationalerts.com/connection/websocket").get();
        session.sendMessage(new TextMessage("{ \"params\": { \"token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDbGllbnQ6MTA3ODYifQ.rXFSO-05afowfbZxvMy-cwPKJ-OPQLsBy4wkoCq_Mi0\" }, \"id\": 1 } "));
        Thread.sleep(20000);
    }

}
