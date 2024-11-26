package pe.joedayz.training.speaker;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import java.util.Collections;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SpeakerResourceTest {

    @Inject //4
    DeterministicIdGenerator idGenerator;

    @Test
    public void testNewSpeaker() {

        UUID uuid = new UUID( 1, 1 );

        idGenerator.setNextUUID( uuid );

        given()
                .body( "{\"nameFirst\": \"Jordi\",\"nameLast\": \"Sola\"}" )
                .contentType( ContentType.JSON )
                .when()
                .post( "/speaker" )
                .then()
                .statusCode( 200 )
                .body( "nameFirst", is( "Jordi" ) )
                .body( "nameLast", is( "Sola" ) )
                .body( "uuid", is( uuid.toString() ) );
    }

    @Test
    public void testListEmptySpeakers() {
        PanacheMock.mock( Speaker.class ); //7
        Mockito.when( Speaker.listAll() ).thenReturn( Collections.emptyList() );//8
        given()
                .when()
                .get( "/speaker" )
                .then()
                .statusCode( 200 )
                .body( "size()", is( 0 ) );
    }

}