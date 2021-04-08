package capstone.zephyr.zephyr.api;

import java.util.ArrayList;

public class APIRequests {

    private long id;
    private String content;
    private boolean response;
    private String message;
    private ArrayList<String> parameterResponse;
    private ArrayList<Integer> voteCountResponse;

    public APIRequests(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public APIRequests(boolean response, String message) {
        this.response = response;
        this.message = message;
    }

    public APIRequests(ArrayList<String> parameterResponse, ArrayList<Integer> voteCountResponse) {
        this.parameterResponse = parameterResponse;
        this.voteCountResponse = voteCountResponse;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<String> getParameters() {
        return parameterResponse;
    }

    public ArrayList<Integer> getVoteCount() {
      return voteCountResponse;
  }
}
