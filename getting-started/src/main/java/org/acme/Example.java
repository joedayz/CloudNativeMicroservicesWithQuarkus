package org.acme;

import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

public class Example {

//    @ConfigProperty(name = "format")
//    Optional<String> format;
////
//    @ConfigProperty(name = "debug-flag", defaultValue = "false")
//    boolean debugFlag;
////
//    @ConfigProperty(name="unit")
//    String unit;

    @Inject
    ExampleConfiguration config;

    public String applyLogic(int value){
        if (config.debugFlag()) {
            System.out.println("The unit is: " + config.unit());
            System.out.println("The value is: " + value);
        }
        return String.format(config.format().orElse("%d"), value);
    }
}
