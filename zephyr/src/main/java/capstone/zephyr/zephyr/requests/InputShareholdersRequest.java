package capstone.zephyr.zephyr.requests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import capstone.zephyr.zephyr.model.ShareholderModel;

public class InputShareholdersRequest {
    private File shareholderCSV;
    private int minimumID;
    private ArrayList<ShareholderModel> shareholders;

    public InputShareholdersRequest(File shareholderCSV) {
        this.shareholderCSV = shareholderCSV;
        this.shareholders = new ArrayList<ShareholderModel>();
    }

    public void setMinimumID(int currentMaxID) {
        minimumID = currentMaxID;
    }

    public void readShareholderFile() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(shareholderCSV));
            String line;

            while ((line = csvReader.readLine()) != null) {
                minimumID = minimumID + 1;
                String[] parsedLine = line.split(",");
                shareholders.add(new ShareholderModel(minimumID, parsedLine[0], parsedLine[1], Integer.parseInt(parsedLine[2])));
            }
            csvReader.close();
            
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ArrayList<ShareholderModel> getShareholders() {
        return shareholders;
    }    
}