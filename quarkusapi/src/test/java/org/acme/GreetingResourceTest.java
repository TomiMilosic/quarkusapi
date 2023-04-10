package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class GreetingResourceTest {


    @Test
    public void testGetAllUsers() {
        // Make a GET request to the /users endpoint
        given()
                .when().get("/users")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$.size()", equalTo(2)); // Assuming that there are 2 users in the database
    }

    @Test
    public void testAddNewUser() {
        // Define the new user to be added
        String requestBody = "{"
                + "\"id\": \"12345\","
                + "\"name\": \"John\","
                + "\"surname\": \"Doe\","
                + "\"reg_st\": \"active\""
                + "}";

        // Make a POST request to the /users endpoint with the new user in the request body
        given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .when().post("/users")
                .then()
                .statusCode(200)
                .body("id", equalTo("12345"))
                .body("name", equalTo("John"))
                .body("surname", equalTo("Doe"))
                .body("reg_st", equalTo("active"));
    }



}