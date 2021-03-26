package capstone.zephyr.zephyr.doa;

import org.springframework.stereotype.Controller;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import capstone.zephyr.zephyr.api.APIRequests;
import capstone.zephyr.zephyr.doa.DatabaseAccess;

@Controller
public class LoginRequest {
    
    private String user_name;
    private String password;

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private DatabaseAccess Query;

    public LoginRequest(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    @PostMapping("/authentication")
    @ResponseBody
    public APIRequests authenticate(@RequestBody LoginRequest request) {
        
        boolean response = false;
        String message = "Not authenticated";

        if (request.user_name == Query.queryDatabase(request.user_name)) {
            if (request.password == Query.queryDatabase(request.password)) {
                
                response = true;
                message = "Correctly authenticated";
            }
        }

        return new APIRequests(response, message);
    }
}