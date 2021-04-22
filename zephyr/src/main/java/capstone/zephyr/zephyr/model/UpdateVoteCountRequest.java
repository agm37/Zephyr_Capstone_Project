package capstone.zephyr.zephyr.model;

public class UpdateVoteCountRequest {
    private int pollID;
    private int voteParameterNum;
    private int voteCount;

    public UpdateVoteCountRequest(int pollID, int voteParameterNum, int voteCount) {
        this.pollID = pollID;
        this.voteParameterNum = voteParameterNum;
        this.voteCount = voteCount;
    }

    public int getPollID() {
        return pollID;
    }

    public int getVoteParameterNum() {
        return voteParameterNum;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
