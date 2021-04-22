package capstone.zephyr.zephyr.model;

public class PollInfoRequest {

    private int pollID;

    public PollInfoRequest(int pollID) {
        this.pollID = pollID;
    }

    public int getPollID() {
        return this.pollID;
    }
}
