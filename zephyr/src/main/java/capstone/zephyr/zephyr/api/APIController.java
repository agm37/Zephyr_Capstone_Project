package capstone.zephyr.zephyr.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import capstone.zephyr.zephyr.dao.DatabaseAccess;
import capstone.zephyr.zephyr.model.LoginModel;
import capstone.zephyr.zephyr.model.ShareholderModel;
import capstone.zephyr.zephyr.requests.ClosePollRequest;
import capstone.zephyr.zephyr.requests.CreatePollRequest;
import capstone.zephyr.zephyr.requests.LoginRequest;
import capstone.zephyr.zephyr.requests.PollInfoRequest;
import capstone.zephyr.zephyr.requests.ShareholderVotingRequest;


@Controller
public class APIController {

    @Autowired
    private DatabaseAccess accessDatabase;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/authentication")
    @ResponseBody
    public APIRequests authenticate(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(token);
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            SecurityContextHolder.clearContext();
            return new APIRequests(false, "Not authenticated");
        }
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new APIRequests(true, "Successfully authenticated");
    }

    @PostMapping("/checkAdmin")
    @ResponseBody
    public int checkAdminStatus(@AuthenticationPrincipal LoginModel login) {
        return accessDatabase.queryAdminStatus(login.getUsername());
    }

    @PostMapping("/getNumShares")
    @ResponseBody
    public int getNumberOfShares(@AuthenticationPrincipal LoginModel login) {
        if (login.getShareholderID().isEmpty()) {
            return 0;
        }
        return accessDatabase.queryShareholderShares(login.getShareholderID().get());
    }

    @PostMapping("/getShareholderInfo")
    @ResponseBody
    public List<Object> getShareholderInfo(@AuthenticationPrincipal LoginModel login) {
        List<Object> results = new ArrayList<>();

        if (login.getShareholderID().isPresent()) {
            int shareholderID = login.getShareholderID().get();
            results.add(accessDatabase.queryShareholderName(shareholderID));
            results.add(accessDatabase.queryShareholderCompany(shareholderID));
            results.add(accessDatabase.queryShareholderShares(shareholderID));
        }
        return results;
    }

    @PostMapping("/pollInfo")
    @ResponseBody
    public APIRequests getPollInfo(@RequestBody PollInfoRequest request) {
        ArrayList<String> parameterResponse = accessDatabase.queryVoteParameter(request.getPollID());
<<<<<<< HEAD
        ArrayList<Integer> voteCountResponse = accessDatabase.queryVoteCount(request.getPollID());
        // if (accessDatabase.queryIsPollClosed(request.getPollID())) {
        //     voteCountResponse = accessDatabase.queryVoteCount(request.getPollID());
        // }
=======
        ArrayList<Integer> voteCountResponse = new ArrayList<Integer>();

        if (accessDatabase.queryIsPollClosed(request.getPollID()) == true) {
            voteCountResponse = accessDatabase.queryVoteCount(request.getPollID());
        }
<<<<<<< HEAD
>>>>>>> b34684c19ee19449c92f73b514e4a5ae3255adea

=======
>>>>>>> 918e2726fd74ee162cf093a930aa33a7ac00334a
        return new APIRequests(parameterResponse, voteCountResponse);
    }

    @PostMapping("/parameterInfo")
    @ResponseBody
    public ArrayList<String> getPollInfo(@RequestBody int pollID) {
        ArrayList<String> parameterResponse = accessDatabase.queryVoteParameter(pollID);
        return parameterResponse;
    }

    @PostMapping("/createPoll")
    @ResponseBody
    public APIRequests createPoll(@RequestBody CreatePollRequest request, @AuthenticationPrincipal LoginModel login) {
        if (login.isAdmin() && accessDatabase.createPoll(request.getPollName(), request.getCompanyName(), request.getParameterNames())) {
            return new APIRequests(true, "Successfully added new Poll");
        }
        else {
            return new APIRequests(false, "Failed to create new Poll");
        }
    }

    @PostMapping("/closePoll")
    @ResponseBody
    public APIRequests closePoll(@RequestBody ClosePollRequest request, @AuthenticationPrincipal LoginModel login) {
        if (login.isAdmin() && accessDatabase.closePoll(request.getPollID())) {
            return new APIRequests(true, "Successfully closed poll");
        } else {
            return new APIRequests(false, "Failed to close poll");
        }
    }

    @PostMapping("/shareholderVote")
    @ResponseBody
    public APIRequests shareholderVoting(@RequestBody ShareholderVotingRequest request, @AuthenticationPrincipal LoginModel login) {
        if (login.getShareholderID().isPresent()
            && !accessDatabase.queryHasShareholderVotedInPoll(
                    login.getShareholderID().get(), request.getPollID())) {
            accessDatabase.recordShareholderVote(login.getShareholderID().get(), request.getPollID(), request.getParameterNum());
            return new APIRequests(true, "Successfully added Votes to Poll");
        } else {
            return new APIRequests(false, "Failed to add Votes");
        }
    }

    @PostMapping("/addShareholders")
    @ResponseBody
    public APIRequests addShareholders(@RequestParam("file") MultipartFile file) {
        String csvData;
        try {
            csvData = new String(file.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
            return new APIRequests(false, "Failed to add shareholders");
        }

        ArrayList<ShareholderModel> shareholders = new ArrayList<>();

        for (String line : csvData.split("\r\n")) {
            if (line.isEmpty()) {
                continue;
            }

            String[] parsedLine = line.split(",");
            shareholders.add(new ShareholderModel(
                parsedLine[0], parsedLine[1],
                Integer.parseInt(parsedLine[2]),
                passwordEncoder.encode(parsedLine[3])));
        }

        accessDatabase.addShareholders(shareholders);
        return new APIRequests(true, "Successfully added Shareholders");
    }
}
