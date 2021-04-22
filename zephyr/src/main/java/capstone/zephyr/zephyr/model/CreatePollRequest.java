package capstone.zephyr.zephyr.model;

public class CreatePollRequest {
    private String pollName;
    private String companyName;

    public CreatePollRequest(String pollName, String companyName) {
        this.pollName = pollName;
        this.companyName = companyName;
    }

    public String getPollName() {
        return pollName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
