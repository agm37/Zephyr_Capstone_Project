package capstone.zephyr.zephyr.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {

    authentication.inMemoryAuthentication()
        .withUser("admin").password("nimda").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic().and()
        .authorizeRequests()
        .antMatchers("/").hasRole("ADMIN")
        .anyRequest().authenticated();
 }
}
