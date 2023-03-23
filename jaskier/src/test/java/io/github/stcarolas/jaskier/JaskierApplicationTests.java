package io.github.stcarolas.jaskier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JaskierApplicationTests {

    @LocalServerPort
	private int port;

    @BeforeEach
    public void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void testAddingEvent() {
        with().
            body(JaskierApplicationTests.class.getClassLoader().getResourceAsStream("recognition-callback.json")).
            header("Content-Type", "application/json").
        when().
            post("/events").
        then().
            statusCode(200);
        when().
            get("/events").
        then().
            log().all();
    }

}
