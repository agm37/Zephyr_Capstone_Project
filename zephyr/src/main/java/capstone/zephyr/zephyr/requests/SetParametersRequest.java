package capstone.zephyr.zephyr.requests;

import java.util.ArrayList;

public class SetParametersRequest {
    private int pollID;
    private ArrayList<String> parameterNames = new ArrayList<String>();

    public SetParametersRequest(int pollID, ArrayList<String> parameterNames) {
        this.pollID = pollID;
        this.parameterNames = parameterNames;
    }

    public int getPollID() {
        return pollID;
    }

    public ArrayList<String> getParameterNames() {
        return parameterNames;
    }
}
