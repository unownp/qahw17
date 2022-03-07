package np.qa.lesson17.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class RestTests {
    @Test
    void delayedResponse() {
        given()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200)
                .body("total", is(12))
                .body("data.id[5]", is(6));
    }

    @Test
    void negativeSingleResource() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404);
    }


    @Test
    void successfulLogin() {

        String data = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }


    @Test
    void negativeLogin() {

        String data = "{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void successfulRegister() {
        String data = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }


}