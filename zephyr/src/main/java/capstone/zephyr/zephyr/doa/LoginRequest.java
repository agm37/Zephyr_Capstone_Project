package capstone.zephyr.zephyr.doa;

import org.springframework.beans.factory.annotation.Autowired;

public class LoginRequest {
    
    private String username;
    private String password;

    @Autowired
    private DatabaseAccess Query;

    public LoginRequest(String user_name, String password) {
        
        this.username = user_name;
        this.password = password;
    }

    public String GetUserName() {
        
        return this.username;
    }

    public String GetPassword() {
        
        return this.password;
    }

    public DatabaseAccess GetQuery() {

        return this.Query;
    }
}