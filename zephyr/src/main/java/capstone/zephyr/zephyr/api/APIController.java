package capstone.zephyr.zephyr.api;

import java.util.ArrayList;
import java.util.List;

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
import capstone.zephyr.zephyr.requests.CreatePollRequest;
import capstone.zephyr.zephyr.requests.LoginRequest;
import capstone.zephyr.zephyr.requests.PollInfoRequest;
import capstone.zephyr.zephyr.requests.SetParametersRequest;
import capstone.zephyr.zephyr.requests.ShareholderInfoRequest;
import capstone.zephyr.zephyr.requests.ShareholderVotingRequest;


@Controller
public class APIController {

    @Autowired
    private DatabaseAccess accessDatabase;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authentication")
    @ResponseBody
    public APIRequests authenticate(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
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

    @PostMapping("/getNumShares")
    @ResponseBody
    public int getNumberOfShares(int shareholder_id) {
        return accessDatabase.queryShareholderShares(shareholder_id);
    }

    @PostMapping("/getShareholderInfo")
    @ResponseBody
    public List<Object> getShareholderInfo(@RequestBody ShareholderInfoRequest request) {
        return request.getShareholderInfo();
    }

    @PostMapping("/pollInfo")
    @ResponseBody
    public APIRequests getPollInfo(@RequestBody PollInfoRequest request) {
        ArrayList<String> parameterResponse = accessDatabase.queryVoteParameter(request.getPollID());
        ArrayList<Integer> voteCountResponse = accessDatabase.queryVoteCount(request.getPollID());

        return new APIRequests(parameterResponse, voteCountResponse);
    }

    @PostMapping("/createPoll")
    @ResponseBody
    public APIRequests createPoll(@RequestBody CreatePollRequest request) {
        Boolean pollCreation = accessDatabase.createPoll(request.getPollName(), request.getCompanyName());

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
            return new APIRequests(true, "Successfully set Parameter Names");
        }
        else {
            return new APIRequests(false, "Failed to set Parameter Names");
        }
    }

    @PostMapping("/shareholderVote")
    @ResponseBody
    public APIRequests shareholderVoting(@RequestBody ShareholderVotingRequest request) {
        if (request.checkEligibility() == true) {
            request.addVotes();
            request.setVoterStatus();
            return new APIRequests(true, "Successfully added Votes to Poll");
        }
        else {
            return new APIRequests(false, "Failed to add Votes");
        }        
    }
}
