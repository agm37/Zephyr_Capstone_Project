package capstone.zephyr.zephyr.model;

import java.util.UUID;

public class User {
    
    private final UUID id;
    private final String name;
    private final int availableVotes;
    
    public User(UUID id, String name, int availableVotes) {
        this.id = id;
        this.name = name;
        this.availableVotes = availableVotes;
    }

    public UUID getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvailableVotes() {
        return availableVotes;
    }
}