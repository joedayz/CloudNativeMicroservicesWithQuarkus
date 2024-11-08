package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class ExpenseValidator {


    @Inject
    ExpenseConfiguration config;

    public void debugRanges() {
        config.debugMessage().ifPresent(System.out::println);
    }

    public boolean isValidAmount(int amount) {
        if (config.debugEnabled()) {
            debugRanges();
        }

        return amount >= config.rangeLow() && amount <= config.rangeHigh();
    }
}

