package capstone.zephyr.zephyr.api;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/zephyr")
  @ResponseBody
  public APIRequests sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
    return new APIRequests(counter.incrementAndGet(), String.format(template, name));
  }
}