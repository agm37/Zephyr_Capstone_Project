package capstone.zephyr.zephyr.doa;

public class VotingRequest {

    private int pollID;

    public VotingRequest(int pollID) {
        this.pollID = pollID;
    }

    public int getPollID() {
        return this.pollID;
    }
}
