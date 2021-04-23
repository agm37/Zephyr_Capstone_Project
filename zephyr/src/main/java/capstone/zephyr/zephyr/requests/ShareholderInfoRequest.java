package capstone.zephyr.zephyr.requests;

import java.util.ArrayList;
import java.util.List;

import capstone.zephyr.zephyr.dao.DatabaseAccess;

public class ShareholderInfoRequest {
    private DatabaseAccess accessDatabase = new DatabaseAccess();
    List<Object> info = new ArrayList <Object>();
    private int shareholderID;

    public ShareholderInfoRequest(int shareholder_id) {
        this.shareholderID = shareholder_id;
    }

    public int getShareholderID() {
        return shareholderID;
    }

    public String getShareholderName() {
        return accessDatabase.queryShareholderName(getShareholderID());
    }

    public String getCompanyName() {
        return accessDatabase.queryShareholderCompany(getShareholderID());
    }

    public int getShareholderShares() {
        return accessDatabase.queryShareholderShares(getShareholderID());
    }

    public List<Object> getShareholderInfo() {
        info.add(getShareholderName());
        info.add(getCompanyName());
        info.add(getShareholderShares());
        return info;
    }
}
