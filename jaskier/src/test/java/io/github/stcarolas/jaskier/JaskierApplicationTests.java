package io.github.stcarolas.jaskier;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JaskierApplicationTests {

    @LocalServerPort
    private int port;

    static final MongoDBContainer mongoDBContainer = new MongoDBContainer(
        DockerImageName.parse("mongo:latest")
    );

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        registry.add(
            "spring.data.mongodb.uri",
            () -> mongoDBContainer.getConnectionString()
        );
    }

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void testAddingEvent() {
        with()
            .body(
                JaskierApplicationTests.class.getClassLoader()
                    .getResourceAsStream("recognition-callback.json")
            )
            .header("Content-Type", "application/json")
            .when()
            .post("/events")
            .then()
            .statusCode(200);

        when().get("/events?channelId=11").then().log().all();
    }
}
