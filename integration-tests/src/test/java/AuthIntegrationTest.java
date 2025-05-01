import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class AuthIntegrationTest {

    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void shouldReturnWithValidToken(){
        // 1. Arrange
        // 2. Act
        // 3. Assert

        String loginPayload = """
                    {
                        "email" : "testuser@test.com",
                        "password" : "password123"
                    }
                """;

        try {
            Response response = given()
                    .contentType("application/json")
                    .body(loginPayload)
                    .when()
                    .post("/auth/login")
                    .then()
                    .statusCode(200)
                    .body("token",notNullValue())
                    .extract()
                    .response();

            System.out.println("Generated Token: "+response.jsonPath().getString("token"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void shouldReturnUnauthorizedOnInvalidLogin(){
        // 1. Arrange
        // 2. Act
        // 3. Assert

        String loginPayload = """
                    {
                        "email" : "testuser983094@test.com",
                        "password" : "password123"
                    }
                """;

        try {
            Response response = given()
                    .contentType("application/json")
                    .body(loginPayload)
                    .when()
                    .post("/auth/login")
                    .then()
                    .statusCode(401)
                    .extract().response();

            System.out.println("Generated Token: ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
