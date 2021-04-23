package capstone.zephyr.zephyr.requests;

public class PollInfoRequest {

    private int pollID;

    public PollInfoRequest(int pollID) {
        this.pollID = pollID;
    }

    public int getPollID() {
        return this.pollID;
    }
}
