package capstone.zephyr.zephyr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ZephyrSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${app.csrf.enabled}")
	private boolean csrfEnabled;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JsonSecurityErrorHandler errorHandler;

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if (!csrfEnabled) {
            http.csrf().disable();
        }

        http
            .authorizeRequests()
                .antMatchers("/authentication").permitAll()
                .anyRequest().authenticated()
                .and()
            .logout()
                .permitAll()
                .and()
            .exceptionHandling()
                .authenticationEntryPoint(errorHandler.authenticationEntryPoint())
                .accessDeniedHandler(errorHandler.accessDeniedHandler());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
