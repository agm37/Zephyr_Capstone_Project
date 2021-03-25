package capstone.zephyr.zephyr.api;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

  @GetMapping("/credentials")
  @ResponseBody
  public APIRequests returnCredentials(@RequestParam(name="name", required=false, defaultValue="Error") String user_name) {
    credentialQuery.queryDatabase();
    user_name = credentialQuery.getQueryResult();
    return new APIRequests(counter.incrementAndGet(), String.format(user_name));
  }

}