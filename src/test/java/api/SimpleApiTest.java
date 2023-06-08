package api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

public class SimpleApiTest {

    @Test
    void simpleApiTest() {
        given()
                .contentType(JSON)
                .auth()
                .oauth2(getAccessToken())
                .get("http://172.18.0.6:8080/api/v1/default_personal/launch")
                .then()
                .log().all()
                .statusCode(SC_OK);
    }

    private String getAccessToken() {
        return given()
                .formParam("grant_type", "password")
                .formParam("username", "default")
                .formParam("password", "1q2w3e")
                .auth()
                .basic("ui", "uiman")
                .post("http://172.18.0.6:8080/uat/sso/oauth/token")
                .then()
                .statusCode(SC_OK)
                .extract()
                .path("access_token");
    }
}
