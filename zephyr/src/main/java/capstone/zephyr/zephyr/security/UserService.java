package capstone.zephyr.zephyr.security;

import org.springframework.beans.factory.annotation.Autowired;
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

        return login;
    }

    public void resetUserPassword(LoginModel user) {
        String newPassword = user.getID() + user.getUsername();
        databaseAccess.updateLoginPasswordById(user.getID(), passwordEncoder.encode(newPassword));
    }
}
