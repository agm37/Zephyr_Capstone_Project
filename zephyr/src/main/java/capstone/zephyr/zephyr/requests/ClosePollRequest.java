package capstone.zephyr.zephyr.requests;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ClosePollRequest {
    private int pollID;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ClosePollRequest(int pollID) {
        this.pollID = pollID;
    }

    public int getPollID() {
        return this.pollID;
    }
}
