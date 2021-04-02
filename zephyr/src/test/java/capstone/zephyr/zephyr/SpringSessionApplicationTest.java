package capstone.zephyr.zephyr;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.http.HttpHeaders;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SpringSessionApplicationTest {

 private TestRestTemplate testRestTemplate;
 private String testUrl = "http://localhost:8080/";


 @Test
 public void testUnauthenticated() {
  RestTemplate restTemplate = new RestTemplate();
  ResponseEntity < String > result = restTemplate.getForEntity(testUrl, String.class);
  assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
 }

 @Test
 public void testSpringSessionAPI() {

  URI uri = URI.create(testUrl);
  RestTemplate restTemplate = new RestTemplate();
  ResponseEntity < String > firstResponse = firstRequest(restTemplate, uri);
  String sessionId1 = firstResponse.getBody();
  String cookie = firstResponse.getHeaders().getFirst("Set-Cookie");
  String sessionId2 = nextRequest(restTemplate, uri, cookie).getBody();
  assertThat(sessionId1).isEqualTo(sessionId2);

 }

 private ResponseEntity < String > firstRequest(RestTemplate restTemplate, URI uri) {
  HttpHeaders headers = new HttpHeaders();
  headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:nimda".getBytes()));
  RequestEntity < Object > request = new RequestEntity < > (headers, HttpMethod.GET, uri);
  return restTemplate.exchange(request, String.class);
 }

 private ResponseEntity < String > nextRequest(RestTemplate restTemplate, URI uri,
  String cookie) {
  HttpHeaders headers = new HttpHeaders();
  headers.set("Cookie", cookie);
  RequestEntity < Object > request = new RequestEntity < > (headers, HttpMethod.GET, uri);
  return restTemplate.exchange(request, String.class);
 }

}
