package capstone.zephyr.zephyr.requests;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginRequest {

    @Autowired
    private AuthenticationManager authenticationManager;

    private String username;
    private String password;
    private Authentication authentication;
    private UsernamePasswordAuthenticationToken token;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserName() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Boolean checkAuthentication() {
        token = new UsernamePasswordAuthenticationToken(getUserName(), getPassword());
        try {
            authentication = authenticationManager.authenticate(token);
        } catch (AuthenticationException ex) {
            SecurityContextHolder.clearContext();
            return false;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }
}