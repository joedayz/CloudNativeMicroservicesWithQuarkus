package org.acme;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.util.Optional;

@ConfigMapping(prefix = "example")
public interface ExampleConfiguration {

    Optional<String> format();

    @WithDefault("false")
    boolean debugFlag();

    String unit();

    ExampleUser user();

    interface ExampleUser {
        String username();
        String email();
    }
}
