package capstone.zephyr.zephyr.requests;

import java.util.ArrayList;

public class CreatePollRequest {
    private String pollName;
    private String companyName;
    private ArrayList<String> parameterNames = new ArrayList<String>();

    public CreatePollRequest(String pollName, String companyName, ArrayList<String> parameterNames) {
        this.pollName = pollName;
        this.companyName = companyName;
        this.parameterNames = parameterNames;
    }

    public String getPollName() {
        return pollName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ArrayList<String> getParameterNames() {
        return parameterNames;
    }
}
