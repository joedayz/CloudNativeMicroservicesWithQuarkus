package pe.joedayz.training;

import pe.joedayz.training.event.EmployeeSignedUp;
import pe.joedayz.training.event.SpeakerWasCreated;
import pe.joedayz.training.event.UpstreamMemberSignedUp;
import pe.joedayz.training.model.Affiliation;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import io.smallrye.reactive.messaging.memory.InMemorySource;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import java.util.List;

import static pe.joedayz.training.SpeakerResourceTest.*;
import static org.awaitility.Awaitility.await;

@QuarkusTest
@QuarkusTestResource(KafkaTestResourceLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReactiveMessagingTest {
    @Inject
    @Any
    InMemoryConnector connector;

    @Test
    public void givenANewRedHatSpeakerAnEventIsFired() {
        InMemorySource<SpeakerWasCreated> eventsIn = connector.source("new-speakers-in");
        InMemorySink<EmployeeSignedUp> eventsOut = connector.sink("employees-out");

        SpeakerWasCreated initialEvent = new SpeakerWasCreated(
                TESTING_ID,
                TESTING_NAME,
                Affiliation.RED_HAT,
                TESTING_EMAIL
        );

        eventsIn.send(initialEvent);

        await().<List<? extends Message<EmployeeSignedUp>>>until(eventsOut::received, t -> t.size() == 1);

        EmployeeSignedUp queuedEvent = eventsOut.received().get(0).getPayload();

        Assertions.assertEquals(TESTING_ID, queuedEvent.speakerId);
        Assertions.assertEquals(TESTING_NAME, queuedEvent.fullName);
        Assertions.assertEquals(TESTING_EMAIL, queuedEvent.email);
    }

    @Test
    public void givenAnUpstreamSpeakerAnEventIsFired() {
        InMemorySource<SpeakerWasCreated> eventsIn = connector.source("new-speakers-in");
        InMemorySink<UpstreamMemberSignedUp> eventsOut = connector.sink("upstream-members-out");

        SpeakerWasCreated initialEvent = new SpeakerWasCreated(
                TESTING_ID,
                TESTING_NAME,
                Affiliation.GNOME_FOUNDATION,
                TESTING_EMAIL
        );

        eventsIn.send(initialEvent);

        await().<List<? extends Message<UpstreamMemberSignedUp>>>until(eventsOut::received, t -> t.size() == 1);

        UpstreamMemberSignedUp queuedEvent = eventsOut.received().get(0).getPayload();

        Assertions.assertEquals(TESTING_ID, queuedEvent.speakerId);
        Assertions.assertEquals(TESTING_NAME, queuedEvent.fullName);
        Assertions.assertEquals(TESTING_EMAIL, queuedEvent.email);
    }
}
