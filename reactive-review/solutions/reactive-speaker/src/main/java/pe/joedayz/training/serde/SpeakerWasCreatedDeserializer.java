package pe.joedayz.training.serde;

import pe.joedayz.training.event.SpeakerWasCreated;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class SpeakerWasCreatedDeserializer
        extends ObjectMapperDeserializer<SpeakerWasCreated> {
    public SpeakerWasCreatedDeserializer() {
        super(SpeakerWasCreated.class);
    }
}
