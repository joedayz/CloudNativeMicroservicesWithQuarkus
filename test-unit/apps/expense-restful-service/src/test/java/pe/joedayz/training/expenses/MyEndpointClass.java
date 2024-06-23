package pe.joedayz.training.expenses;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MyEndpointClass {

    @Test
    public void testMyEndpoint() {
        given()
          .when()
                .get("/expenses/hello")
          .then()
             .body(is("Hello JoeDayz"));
    }
}
