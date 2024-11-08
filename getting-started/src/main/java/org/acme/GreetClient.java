package org.acme;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/greet")
//@RegisterRestClient(baseUri = "http://gree-service.example.com")
@RegisterRestClient(configKey = "greeter-api")
public interface GreetClient {

  @GET
  String greet();
}
