package capstone.zephyr.zephyr.api;

public class APIRequests {
    
      private final long id;
      private final String content;
    
      public APIRequests(long id, String content) {
        this.id = id;
        this.content = content;
      }
    
      public long getId() {
        return id;
      }
    
      public String getContent() {
        return content;
      }    
    }