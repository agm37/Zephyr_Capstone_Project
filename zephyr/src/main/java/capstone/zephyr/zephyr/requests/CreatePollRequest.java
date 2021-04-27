package capstone.zephyr.zephyr.requests;

import java.util.ArrayList;

import capstone.zephyr.zephyr.dao.DatabaseAccess;

public class CreatePollRequest {
    private String pollName;
    private String companyName;
    private ArrayList<String> parameterNames = new ArrayList<String>();
    private int pollID;
    private DatabaseAccess accessDatabase;

    public CreatePollRequest(String pollName, String companyName, ArrayList<String> parameterNames) {
        this.pollName = pollName;
        this.companyName = companyName;
        this.parameterNames = parameterNames;
        this.pollID = accessDatabase.getNewPollID();
    }

    public String getPollName() {
        return pollName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getPollID() {
        return pollID;
    }

    public ArrayList<String> getParameterNames() {
        return parameterNames;
    }
}
