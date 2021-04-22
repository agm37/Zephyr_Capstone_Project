package capstone.zephyr.zephyr.api;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import capstone.zephyr.zephyr.dao.DatabaseAccess;
import capstone.zephyr.zephyr.model.LoginRequest;
import capstone.zephyr.zephyr.model.CreatePollRequest;
import capstone.zephyr.zephyr.model.PollInfoRequest;
import capstone.zephyr.zephyr.model.SetParametersRequest;
import capstone.zephyr.zephyr.model.ShareholderVotingRequest;
import capstone.zephyr.zephyr.model.UpdateVoteCountRequest;


@Controller
public class APIController {

    @Autowired
    private DatabaseAccess accessDatabase;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authentication")
    @ResponseBody
    public APIRequests authenticate(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            request.getUserName(), request.getPassword());
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(token);
        } catch (AuthenticationException ex) {
            SecurityContextHolder.clearContext();
            return new APIRequests(false, "Not authenticated");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new APIRequests(true, "Successfully authenticated");
    }

    @PostMapping("/checkAdmin")
    @ResponseBody
    public int checkAdminStatus(String user_name) {
        return accessDatabase.queryAdminStatus(user_name);
    }

    @GetMapping("/pollInfo")
    @ResponseBody
    public APIRequests getPollInfo(@RequestBody PollInfoRequest request) {
        ArrayList<String> parameterResponse = accessDatabase.queryVoteParameter(request.getPollID());
        ArrayList<Integer> voteCountResponse = accessDatabase.queryVoteCount(request.getPollID());

        return new APIRequests(parameterResponse, voteCountResponse);
    }

    @PostMapping("/createPoll")
    @ResponseBody
    public APIRequests createPoll(@RequestBody CreatePollRequest request) {
        Boolean pollCreation = accessDatabase.createPoll(request.getPollName(), request.getCompanyName(), request.getNumberShareholders());

        if (pollCreation == true) {
            return new APIRequests(true, "Successfully added new Poll");
        }
        else {
            return new APIRequests(false, "Failed to create new Poll");
        }
    }

    @PostMapping("/setParameters")
    @ResponseBody
    public APIRequests setVoteParameters(@RequestBody SetParametersRequest request) {
        Boolean setParameters = accessDatabase.setVoteParameters(request.getPollID(), request.getParameterNames());

        if (setParameters == true) {
            return new APIRequests(true, "Successfully added new Poll");
        }
        else {
            return new APIRequests(false, "Failed to create new Poll");
        }
    }

    @PostMapping("/updateVoteCount")
    @ResponseBody
    public APIRequests updateVoteCount(@RequestBody UpdateVoteCountRequest request) {
        accessDatabase.updateVotes(request.getPollID(), request.getVoteParameterNum(), request.getVoteCount());

        return new APIRequests(true,"Successfully updated Vote Count");
    }

    @PostMapping("/shareholderVote")
    @ResponseBody
    public APIRequests shareholderVoting(@RequestBody ShareholderVotingRequest request) {
        if (request.checkEligibility() == true) {
            request.addVotes();
            return new APIRequests(true, "Successfully added Votes to Poll");
        }
        else {
            return new APIRequests(false, "Failed to add Votes");
        }
        
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

  @RequestMapping("/hello")
    public String hello(@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter, HttpServletResponse response) {
        hitCounter++;

        Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
        response.addCookie(cookie);

        return "I AM RESPONDING";
    }

  */
}
