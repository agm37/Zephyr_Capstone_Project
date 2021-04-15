package capstone.zephyr.zephyr.security.migration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import capstone.zephyr.zephyr.doa.DatabaseAccess;
import capstone.zephyr.zephyr.model.LoginModel;
import capstone.zephyr.zephyr.security.UserService;

@Component
public class ResetAllPasswords implements CommandLineRunner {
    @Value("${app.util.reset-passwords:false}")
    private boolean resetPasswords;

    @Autowired
    private UserService userService;

    @Autowired
    private DatabaseAccess databaseAccess;

    private Logger logger = LoggerFactory.getLogger(ResetAllPasswords.class);

    @Override
    public void run(String... args) throws Exception {
        if (!resetPasswords) {
            return;
        }

        logger.info("Resetting all passwords...");

        for (LoginModel login : databaseAccess.queryAllLogins()) {
            userService.resetUserPassword(login);
        }

        logger.info("All passwords reset.");
    }
}
