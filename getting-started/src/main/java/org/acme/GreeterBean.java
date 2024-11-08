package org.acme;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GreeterBean {

  @Inject
  GreeterService greeterService;

  public void greet(){
    greeterService.greet();
  }
}
