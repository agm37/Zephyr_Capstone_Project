package capstone.zephyr.zephyr.session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    /**
     * Simple Session controller which will return session ID backed by Spring Session API
     * @param session
     * @return session ID
     */
    @GetMapping("/")
    String uid(HttpSession session) {
        return session.getId();
    }

}