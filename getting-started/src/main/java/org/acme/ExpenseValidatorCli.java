package org.acme;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class ExpenseValidatorCli implements QuarkusApplication {
    @Inject
    ExpenseValidator validator;

    @Override
    public int run(String... args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("The command requires 1 argument");
        }

        try {
            int amountValue = Integer.parseInt(args[0]);

            if (validator.isValidAmount(amountValue)) {
                System.out.println("Valid amount: " + amountValue);
                Quarkus.waitForExit();
                return 0;
            }

            System.out.println("Invalid amount: " + amountValue);

            Quarkus.waitForExit();
            return 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The argument must be an integer");
        }
    }
}