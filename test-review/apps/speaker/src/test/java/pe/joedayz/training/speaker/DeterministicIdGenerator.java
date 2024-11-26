package pe.joedayz.training.speaker;

import io.quarkus.test.Mock;
import jakarta.inject.Singleton;
import pe.joedayz.training.speaker.idgenerator.IdGenerator;

import java.util.UUID;

@Mock //5
@Singleton //6
public class DeterministicIdGenerator implements IdGenerator {

    private UUID nextUUID = new UUID( 0, 0 );

    public String generate() {
        UUID result = nextUUID;
        nextUUID = null;
        return result.toString();
    }

    public void setNextUUID( final UUID nextUUID ) {
        this.nextUUID = nextUUID;
    }

}

