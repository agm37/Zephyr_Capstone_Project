package capstone.zephyr.zephyr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import capstone.zephyr.zephyr.doa.DatabaseAccess;

@Controller
public class UserService implements UserDetailsService {
    @Autowired
    private DatabaseAccess databaseAccess;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        UserDetails details = databaseAccess.queryUserDetailsForUserName(user);
        if (details == null) {
            throw new UsernameNotFoundException("Unknown user: " + user);
        }

        return details;
    }
}
