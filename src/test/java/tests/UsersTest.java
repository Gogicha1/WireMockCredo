package tests;

import data.Constants;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.WireMockSetup;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UsersTest {
    WireMockSetup wireMock = new WireMockSetup();

    @BeforeClass
    public void setup() {
        wireMock.startServer();
        RestAssured.baseURI = Constants.BASE_URI;
    }

    @AfterClass
    public void teardown() {
        wireMock.stopServer();
    }

    @Test
    public void testGetUsers200() {
        wireMock.stubGetUsers200();

        get("/users")
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("Saba"));
    }

    @Test
    public void testGetUsers500() {
        wireMock.stubGetUsers500();

        get("/users")
                .then()
                .statusCode(500);
    }
}

