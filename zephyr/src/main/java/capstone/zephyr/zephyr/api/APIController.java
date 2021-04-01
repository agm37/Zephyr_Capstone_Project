package capstone.zephyr.zephyr.api;

import java.util.concurrent.atomic.AtomicLong;

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

import capstone.zephyr.zephyr.doa.DatabaseAccess;
import capstone.zephyr.zephyr.doa.LoginRequest;

@Controller
public class APIController {

  @Autowired
  private DatabaseAccess credentialQuery;
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/zephyr")
  @ResponseBody
  public APIRequests sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
    String template = "Hello, %s!";
    return new APIRequests(counter.incrementAndGet(), String.format(template, name));
  }

  @GetMapping("/credentials/{name}")
  @ResponseBody
  public APIRequests returnCredentials(@PathVariable String name) {
    String user_name = credentialQuery.queryUserName(name);
    return new APIRequests(counter.incrementAndGet(), String.format(user_name));
  }

  @PostMapping("/authentication")
  @ResponseBody
  public APIRequests authenticate(@RequestBody LoginRequest request) {

    boolean response = false;
    String message = "Not authenticated";

    if (request.GetPassword().equals(credentialQuery.queryPasswordForUserName(request.GetUserName()))) {
      response = true;
      message = "Correctly authenticated";
    }
    return new APIRequests(response, message);
  }

  /*@PostMapping("/voting")
  @ResponseBody
  public APIRequests returnShares(@RequestBody) {

    
    return new APIRequests(shares);
  }*/

  @RequestMapping(value = "/hello")
  public String hello(@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter, HttpServletResponse response) {

    hitCounter++;

    Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
    response.addCookie(cookie);

    return "I AM RESPONDING";
  }
}
