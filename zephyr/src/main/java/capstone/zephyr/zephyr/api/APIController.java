package capstone.zephyr.zephyr.api;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;

import capstone.zephyr.zephyr.doa.DatabaseAccess;

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

  @PostMapping("/authentication")
  @ResponseBody
  public APIRequests authenticate(@RequestParam(name="name", required=false, defaultValue="You don't know who you are, and neither do we") String name) {
    return new APIRequests(counter.incrementAndGet(), String.format(name));
  }
}
