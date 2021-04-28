package capstone.zephyr.zephyr.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginModel implements UserDetails {
    public static final String ADMIN_ROLE = "admin";

    private int id;
    private String username;
    private String password;
    private boolean isAdmin;
    private Optional<Integer> shareholderID;
    private Collection<SimpleGrantedAuthority> authorities;

    public LoginModel(int id, String username, String password, boolean isAdmin,
                      Optional<Integer> shareholderID) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.shareholderID = shareholderID;

        this.authorities = new ArrayList<>();
        if (isAdmin) {
            authorities.add(new SimpleGrantedAuthority(ADMIN_ROLE));
        }
    }

    public int getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public Optional<Integer> getShareholderID() {
        return shareholderID;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
