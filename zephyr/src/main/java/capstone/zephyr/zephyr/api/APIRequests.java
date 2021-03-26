package capstone.zephyr.zephyr.api;

public class APIRequests {
    
      private long id;
      private String content;
      boolean response;
      String message;
    
      public APIRequests(long id, String content) {
        this.id = id;
        this.content = content;
      }

      public APIRequests(boolean response, String message) {
        this.response = response;
        this.message = message;
      }
    
      public long getId() {
        return id;
      }
    
      public String getContent() {
        return content;
      }    
    }