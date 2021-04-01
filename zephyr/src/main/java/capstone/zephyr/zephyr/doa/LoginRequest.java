package capstone.zephyr.zephyr.doa;

public class LoginRequest {

    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String GetUserName() {
        return this.username;
    }

    public String GetPassword() {
        return this.password;
    }
}
