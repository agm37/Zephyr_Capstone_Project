package capstone.zephyr.zephyr.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;

import capstone.zephyr.zephyr.doa.DatabaseAccess;
import capstone.zephyr.zephyr.doa.LoginRequest;
import capstone.zephyr.zephyr.doa.VotingRequest;

@Controller
public class APIController {

    @Autowired
    private DatabaseAccess accessDatabase;

    @PostMapping("/authentication")
    @ResponseBody
    public APIRequests authenticate(@RequestBody LoginRequest request) {
        boolean response = false;
        String message = "Not authenticated";

        int authenticatePassword = accessDatabase.queryPasswordAuthenticate(request.getPassword());

        if (authenticatePassword == 1) {
            response = true;
            message = "Successfully authenticated";
        }
        return new APIRequests(response, message);
    }

    @PostMapping("/pollInfo")
    @ResponseBody
    public APIRequests getPollInfo(@RequestBody VotingRequest request) {
        ArrayList<String> parameterResponse = accessDatabase.queryVoteParameter(request.getPollID());
        ArrayList<Integer> voteCountResponse = accessDatabase.queryVoteCount(request.getPollID());

        return new APIRequests(parameterResponse, voteCountResponse);
    }

    @RequestMapping("/hello")
    public String hello(@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter, HttpServletResponse response) {
        hitCounter++;

        Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
        response.addCookie(cookie);

        return "I AM RESPONDING";
    }



  //Test Code/Templates
  /*

  @GetMapping("/zephyr")
  @ResponseBody
  public APIRequests sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
    String template = "Hello, %s!";
    return new APIRequests(counter.incrementAndGet(), String.format(template, name));
  }

  @GetMapping("/credentials/{name}")
  @ResponseBody
  public APIRequests returnCredentials(@PathVariable String name) {
    String user_name = accessDatabase.queryUserName(name);
    return new APIRequests(counter.incrementAndGet(), String.format(user_name));
  }

  */
}
