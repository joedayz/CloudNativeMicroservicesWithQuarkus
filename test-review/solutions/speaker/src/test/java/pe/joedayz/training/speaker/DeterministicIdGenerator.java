package pe.joedayz.training.speaker;

import java.util.UUID;
import jakarta.inject.Singleton;
import io.quarkus.test.Mock;
import pe.joedayz.training.speaker.idgenerator.IdGenerator;


@Mock
@Singleton
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
