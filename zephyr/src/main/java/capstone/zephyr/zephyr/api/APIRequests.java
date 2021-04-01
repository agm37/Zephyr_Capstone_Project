package capstone.zephyr.zephyr.api;

public class APIRequests {

      private long id;
      private String content;
      private boolean response;
      private String message;
      private int shares;

      public APIRequests(long id, String content) {
        this.id = id;
        this.content = content;
      }

      public APIRequests(boolean response, String message) {
        this.response = response;
        this.message = message;
      }

      public APIRequests(int shares) {
        this.shares = shares;
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
    }
