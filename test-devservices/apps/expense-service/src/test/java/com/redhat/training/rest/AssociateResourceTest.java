package com.redhat.training.rest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.redhat.training.model.Associate;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint( AssociateResource.class )
public class AssociateResourceTest {

	@Test
	public void testListAllEndpoint() {
		Associate[] associates = given()
				.when().get()
				.then()
					.statusCode( 200 )
					.extract()
					.as( Associate[].class );
		assertThat( associates ).hasSize(2);
	}
}
