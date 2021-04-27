package capstone.zephyr.zephyr.security;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import capstone.zephyr.zephyr.dao.DatabaseAccess;
import capstone.zephyr.zephyr.model.LoginModel;

@Controller
public class UserService implements UserDetailsService {
    public static final String ADMIN_ROLE = "admin";

    @Autowired
    private DatabaseAccess databaseAccess;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        LoginModel login = databaseAccess.queryLoginByUserName(user);
        if (login == null) {
            throw new UsernameNotFoundException("Unknown user: " + user);
        }

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (login.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority(ADMIN_ROLE));
        }

        return new User(login.getName(), login.getPassword(), authorities);
    }

    public void resetUserPassword(LoginModel user) {
        String newPassword = user.getID() + user.getName();
        databaseAccess.updateLoginPasswordById(user.getID(), passwordEncoder.encode(newPassword));
    }
}
