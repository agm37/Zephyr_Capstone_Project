package capstone.zephyr.zephyr.model;

public class ShareholderModel {
    private int shareholder_id;
    private String shareholder_name;
    private String company_name;
    private int num_shares;

    public ShareholderModel(int shareholder_id, String shareholder_name, String company_name, int num_shares) {
        this.shareholder_id = shareholder_id;
        this.shareholder_name = shareholder_name;
        this.company_name = company_name;
        this.num_shares = num_shares;
    }

    public int getShareholderID() {
        return shareholder_id;
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
}
