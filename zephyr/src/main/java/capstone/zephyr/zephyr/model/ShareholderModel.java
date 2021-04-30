package capstone.zephyr.zephyr.model;

public class ShareholderModel {
    private String shareholder_name;
    private String company_name;
    private int num_shares;
    private String default_password;

    public ShareholderModel(String shareholder_name, String company_name,
                            int num_shares, String default_password) {
        this.shareholder_name = shareholder_name;
        this.company_name = company_name;
        this.num_shares = num_shares;
        this.default_password = default_password;
    }

    public String getShareholderName() {
        return shareholder_name;
    }

    public String getCompanyName() {
        return company_name;
    }

    public int getNumberOfShares() {
        return num_shares;
    }

    public String getDefaultPassword() {
        return default_password;
    }
}
