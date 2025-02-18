package pe.joedayz.training;

import pe.joedayz.training.event.BankAccountWasCreated;
import pe.joedayz.training.event.HighRiskAccountWasDetected;
import pe.joedayz.training.event.LowRiskAccountWasDetected;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import io.smallrye.reactive.messaging.memory.InMemorySource;
import org.junit.jupiter.api.*;

import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;

@QuarkusTest
@QuarkusTestResource(KafkaTestResourceLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FraudScoreNotificationTest {
    @Inject
    @Any
    InMemoryConnector connector;

    @Test
    @Order(1)
    void testNotificationForNoneRiskAlerts() {
        InMemorySource<BankAccountWasCreated> newAccountsIn = connector.source("new-bank-accounts-in");
        InMemorySink<LowRiskAccountWasDetected> lowRiskAlertsOut = connector.sink("low-risk-alerts-out");
        InMemorySink<HighRiskAccountWasDetected> highRiskAlertsOut = connector.sink("high-risk-alerts-out");

        BankAccountWasCreated triggerEvent = new BankAccountWasCreated(100L, 100L);
        newAccountsIn.send(triggerEvent);

        Assertions.assertEquals(0, lowRiskAlertsOut.received().size());
        Assertions.assertEquals(0, highRiskAlertsOut.received().size());
    }

    @Test
    @Order(2)
    void testNotificationForLowRiskAlerts() {
        InMemorySource<BankAccountWasCreated> newAccountsIn = connector.source("new-bank-accounts-in");
        InMemorySink<LowRiskAccountWasDetected> lowRiskAlertsOut = connector.sink("low-risk-alerts-out");

        BankAccountWasCreated triggerEvent = new BankAccountWasCreated(200L, 5000L);
        newAccountsIn.send(triggerEvent);

        Assertions.assertEquals(1, lowRiskAlertsOut.received().size());
    }

    @Test
    @Order(3)
    void testNotificationForHighRiskAlerts() {
        InMemorySource<BankAccountWasCreated> newAccountsIn = connector.source("new-bank-accounts-in");
        InMemorySink<HighRiskAccountWasDetected> highRiskAlertsOut = connector.sink("high-risk-alerts-out");

        BankAccountWasCreated triggerEvent = new BankAccountWasCreated(200L, 200000L);
        newAccountsIn.send(triggerEvent);

        Assertions.assertEquals(1, highRiskAlertsOut.received().size());
    }
}
