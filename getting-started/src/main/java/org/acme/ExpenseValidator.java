package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class ExpenseValidator {

//    private static final boolean DEBUG_ENABLED = true;
//
//    private static final int RANGE_HIGH = 1000;
//
//    private static final int RANGE_LOW = 250;

//    @ConfigProperty(name = "debug-enabled", defaultValue = "false")
//    boolean debug;
//    @ConfigProperty(name = "range-high")
//    int targetRangeHigh;
//    @ConfigProperty(name = "range-low")
//    int targetRangeLow;

    @Inject
    ExpenseConfiguration config;

    public void debugRanges() {
        config.debugMessage().ifPresent(System.out::println);
    }
    public boolean isValidAmount(int amount) {
        if (config.debugEnabled()) {
            debugRanges();
        }
        return amount >=  config.rangeLow() && amount <= config.rangeHigh();
    }
}
