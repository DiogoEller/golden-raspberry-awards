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

    @Test
    void testWinnersEndpoint() {
        given()
            .port(port)
        .when()
            .get("/api/winners")
        .then()
            .statusCode(200)
            .body("[0].title", equalTo("Can't Stop the Music"))
            .body("[0].year", equalTo(1980))
            .body("[0].studios", equalTo("Associated Film Distribution"))
            .body("[0].producers", equalTo("Allan Carr"))
            .body("[0].winner", equalTo(true))

            .body("[1].title", equalTo("Mommie Dearest"))
            .body("[1].year", equalTo(1981))
            .body("[1].studios", equalTo("Paramount Pictures"))
            .body("[1].producers", equalTo("Frank Yablans"))
            .body("[1].winner", equalTo(true))
            
            .body("[2].title", equalTo("Inchon"))
            .body("[2].year", equalTo(1982))
            .body("[2].studios", equalTo("MGM"))
            .body("[2].producers", equalTo("Mitsuharu Ishii"))
            .body("[2].winner", equalTo(true))
            
            .body("[3].title", equalTo("The Lonely Lady"))
            .body("[3].year", equalTo(1983))
            .body("[3].studios", equalTo("Universal Studios"))
            .body("[3].producers", equalTo("Robert R. Weston"))
            .body("[3].winner", equalTo(true));
    }

    @Test
    void testMoviesEndpoint() {
        given()
            .port(port)
        .when()
            .get("/api/movies")
        .then()
            .statusCode(200)
            .body("[0].title", equalTo("Can't Stop the Music"))
            .body("[0].year", equalTo(1980))
            .body("[0].studios", equalTo("Associated Film Distribution"))
            .body("[0].producers", equalTo("Allan Carr"))
            .body("[0].winner", equalTo(true))

            .body("[1].title", equalTo("Cruising"))
            .body("[1].year", equalTo(1980))
            .body("[1].studios", equalTo("Lorimar Productions, United Artists"))
            .body("[1].producers", equalTo("Jerry Weintraub"))
            .body("[1].winner", equalTo(false))
            
            .body("[2].title", equalTo("The Formula"))
            .body("[2].year", equalTo(1980))
            .body("[2].studios", equalTo("MGM, United Artists"))
            .body("[2].producers", equalTo("Steve Shagan"))
            .body("[2].winner", equalTo(false))
            
            .body("[3].title", equalTo("Friday the 13th"))
            .body("[3].year", equalTo(1980))
            .body("[3].studios", equalTo("Paramount Pictures"))
            .body("[3].producers", equalTo("Sean S. Cunningham"))
            .body("[3].winner", equalTo(false));
    }
}
