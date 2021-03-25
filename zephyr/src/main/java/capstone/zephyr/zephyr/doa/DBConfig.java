package capstone.zephyr.zephyr.doa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)

public class DBConfig {
    
    @Autowired
    private Environment databaseEnvironment;

    @Bean
    public DataSource dataSource() {

        var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseEnvironment.getProperty("spring.datasource.driver"));
        dataSource.setUrl(databaseEnvironment.getProperty("spring.datasource.url"));
        dataSource.setUsername(databaseEnvironment.getProperty("spring.datasource.username"));
        dataSource.setPassword(databaseEnvironment.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {

        var databaseTemplate = new JdbcTemplate();
        databaseTemplate.setDataSource(dataSource());

        return databaseTemplate;
    }
}