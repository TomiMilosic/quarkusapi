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
                .body("$.size()", equalTo(1)); // Assuming that there are 2 users in the database
    }



    @Test
    public void testDeleteUser() {
        // First, add a new user to the database for testing purposes
        String requestBody = "{"
                + "\"id\": \"123456\","
                + "\"name\": \"John\","
                + "\"surname\": \"Doe\","
                + "\"reg_st\": \"active\""
                + "}";
        given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .when().post("/users")
                .then()
                .statusCode(200);

        // Then, make a DELETE request to delete the user with ID "12345"
        given()
                .when().delete("/users/123456")
                .then()
                .statusCode(204);

        // Finally, check that the user was actually deleted by attempting to retrieve it
        given()
                .when().get("/users/123456")
                .then()
                .statusCode(204);
    }

}