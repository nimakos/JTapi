package gr.nikolis.JTapi.security;

import gr.nikolis.JTapi.constants.mappings.HomeMappings;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String [] permitted = {
            HomeMappings.HOME,
            HomeMappings.HOME_DEFAULT
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(permitted).permitAll().anyRequest().authenticated();
    }
}
