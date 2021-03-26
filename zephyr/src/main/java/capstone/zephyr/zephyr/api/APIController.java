package capstone.zephyr.zephyr.api;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import capstone.zephyr.zephyr.doa.DatabaseAccess;
import capstone.zephyr.zephyr.doa.LoginRequest;

@Controller
public class APIController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();
  @Autowired
  private DatabaseAccess credentialQuery;

  @GetMapping("/zephyr")
  @ResponseBody
  public APIRequests sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
    return new APIRequests(counter.incrementAndGet(), String.format(template, name));
  }

  @GetMapping("/credentials/{name}")
  @ResponseBody
  public APIRequests returnCredentials(@PathVariable String name) {
    String user_name = credentialQuery.queryDatabase(name);
    return new APIRequests(counter.incrementAndGet(), String.format(user_name));
  }

  @PostMapping("/authentication")
    @ResponseBody
    public APIRequests authenticate(@RequestBody LoginRequest request) {
        
        boolean response = false;
        String message = "Not authenticated";

        if (request.GetUserName() == request.GetQuery().queryDatabase(request.GetUserName())) {
            if (request.GetPassword() == request.GetQuery().queryDatabase(request.GetUserName())) {
                
                response = true;
                message = "Correctly authenticated";
            }
        }

        return new APIRequests(response, message);
    }

}