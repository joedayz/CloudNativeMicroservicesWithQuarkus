package pe.joedayz.training.expenses;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@QuarkusTest
public class MyTestClass {


    //@TestHTTPResource("index.html") // http://localhost:8080/index.html
    URL resource;
    //@TestHTTPEndpoint(MyService.class)
    //@Test
    public void testResource() throws IOException {
        try(InputStream in = resource.openStream()){
            String contents = new BufferedReader(new InputStreamReader(in))
                    .lines()
                    .collect(Collectors.joining("\n"));
            assertTrue(contents.contains("Static assets"));
        }
    }
//  @TestHTTPEndpoint(MyService.class)
//  @TestHTTPResource
//  URL endpoint;
//
//  @Test
//  public void testMyServiceEndPoint() {
//    given()
//        .when()
//        .get("/me")
//        .then()
//        .body(is("hello"));
//  }
}
