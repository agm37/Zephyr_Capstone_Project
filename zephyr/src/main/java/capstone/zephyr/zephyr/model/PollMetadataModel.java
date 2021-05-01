package capstone.zephyr.zephyr.model;

public class PollMetadataModel {
    private int pollID;
    private String pollName;
    private boolean isActive;
    private boolean alreadyVoted;

    public PollMetadataModel(int pollID, String pollName, boolean isActive, boolean alreadyVoted) {
        this.pollID = pollID;
        this.pollName = pollName;
        this.isActive = isActive;
        this.alreadyVoted = alreadyVoted;
    }

    public int getPollID() {
        return pollID;
    }

    public String getPollName() {
        return pollName;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean getAlreadyVoted() {
        return alreadyVoted;
    }
}
