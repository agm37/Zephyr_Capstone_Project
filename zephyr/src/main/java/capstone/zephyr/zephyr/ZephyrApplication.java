package capstone.zephyr.zephyr;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.session.MapSessionRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@PropertySource("classpath:secret.properties")
public class ZephyrApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZephyrApplication.class, args);
	}

	@Value("${app.cors.allowed-origins}")
	private String[] corsAllowedOrigins;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
					.addMapping("/**")
					.allowedOrigins(corsAllowedOrigins)
					.allowCredentials(true);
			}
		};
	}

	@Bean
  public MapSessionRepository sessionRepository() {
    return new MapSessionRepository(new ConcurrentHashMap<>());
  }
}
