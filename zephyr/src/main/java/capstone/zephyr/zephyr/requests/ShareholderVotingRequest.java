package capstone.zephyr.zephyr.requests;

public class ShareholderVotingRequest {
    private int shareholderID;
    private int pollID;
    private int parameterNum;

    public ShareholderVotingRequest(int pollID, int parameterNum) {
        this.pollID = pollID;
        this.parameterNum = parameterNum;
    }

    public int getShareholderID() {
        return shareholderID;
    }

    public int getPollID() {
        return pollID;
    }

    public int getParameterNum() {
        return parameterNum;
    }
}
