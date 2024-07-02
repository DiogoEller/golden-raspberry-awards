package com.example.goldenraspberryawards;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    void testProducersEndpoint() {
        given()
            .port(port)
        .when()
            .get("/api/producers")
        .then()
            .statusCode(200)
            .body("min[0].producer", equalTo("Joel Silver"))
            .body("min[0].interval", equalTo(1))
            .body("min[0].previousWin", equalTo(1990))
            .body("min[0].followingWin", equalTo(1991))
            
            .body("max[0].producer", equalTo("Matthew Vaughn"))
            .body("max[0].interval", equalTo(13))
            .body("max[0].previousWin", equalTo(2002))
            .body("max[0].followingWin", equalTo(2015));
    }
}
