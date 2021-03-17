package capstone.zephyr.zephyr.model;

import java.util.UUID;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class User {
    
    private final UUID id;
    private final String name;
    
    public User(UUID id, String name) {

        this.id = id;
        this.name = name;
    }

    public UUID getID() {

        return id;
    }

    public String getName() {

        return name;
    }

}
