package Prototypes;

import java.util.UUID;

public abstract class IDGenerator {
    // ID Generator Variables
    protected UUID uuid;

    /**
     * Generates an ID Set
     * @return
     */
    protected UUID generateID(){
        UUID uuid = UUID.randomUUID();

        return uuid;
    }
}
