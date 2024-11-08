package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class GreeterService {

  @Inject
      @RestClient
  GreetClient greetClient;

  public String invokeGreeter() {
    return greetClient.greet();
  }
}
