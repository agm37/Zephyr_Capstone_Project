package capstone.zephyr.zephyr.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsProcessor;
import org.springframework.web.cors.DefaultCorsProcessor;

import capstone.zephyr.zephyr.api.APIRequests;

@Component
public class JsonSecurityErrorHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CorsConfigurationSource corsSource;

    private final CorsProcessor corsProcessor = new DefaultCorsProcessor();

    private void fillResponse(HttpServletRequest request, HttpServletResponse response,
            HttpStatus status) throws IOException {
        CorsConfiguration configuration = corsSource.getCorsConfiguration(request);
        corsProcessor.processRequest(configuration, request, response);

        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setStatus(status.value());
        mapper.writeValue(response.getWriter(), new APIRequests(false, status.getReasonPhrase()));
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        fillResponse(request, response, HttpStatus.FORBIDDEN);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        fillResponse(request, response, HttpStatus.UNAUTHORIZED);
    }
}
