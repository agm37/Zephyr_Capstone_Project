package capstone.zephyr.zephyr.model;

public class CreatePollRequest {
    private String pollName;
    private String companyName;
    private int numberShareholders;

    public CreatePollRequest(String pollName, String companyName, int numberShareholders) {
        this.pollName = pollName;
        this.companyName = companyName;
        this.numberShareholders = numberShareholders;
    }

    public String getPollName() {
        return pollName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getNumberShareholders() {
        return numberShareholders;
    }
}
