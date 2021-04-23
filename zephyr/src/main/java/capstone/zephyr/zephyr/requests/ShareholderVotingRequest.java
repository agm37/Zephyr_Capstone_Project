package capstone.zephyr.zephyr.requests;

import capstone.zephyr.zephyr.dao.DatabaseAccess;

public class ShareholderVotingRequest {
    private DatabaseAccess accessDatabase = new DatabaseAccess();
    private int shareholderID;
    private int pollID;
    private int parameterNum;
    private int voteCount;

    public ShareholderVotingRequest(int shareholderID, int pollID, int parameterNum, int voteCount) {
        this.shareholderID = shareholderID;
        this.pollID = pollID;
        this.parameterNum = parameterNum;
        this.voteCount = voteCount;
    }

    public Boolean checkEligibility() {
        if ((accessDatabase.queryShareholderEligibility(getShareholderID()) == 0) && (getVoteCount() <= accessDatabase.queryShareholderShares(getShareholderID()))) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addVotes() {
        accessDatabase.updateVotes(getPollID(), getParameterNum(), getVoteCount());
    }

    public void setVoterStatus() {
        accessDatabase.updateShareholderEligibility(getShareholderID());
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

    public int getVoteCount() {
        return voteCount;
    }
}
