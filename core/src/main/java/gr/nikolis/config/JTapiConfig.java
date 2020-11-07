package gr.nikolis.config;

import gr.nikolis.qualifiers.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jtapi.properties")
@Slf4j
public class JTapiConfig {

    // == fields ==
    @Value("${user.login}")
    private String userLoginName;

    @Value("${user.password}")
    private String userLoginPassword;

    @Value("${server.host.name}")
    private String serverHostName;

    @Value("${jtapi.peer}")
    private String jTapiPeer;

    @Value("${jtapi.ext.number}")
    private String jTapiExtNumber;

    @Bean
    @UserName
    public String getUserLoginName() {
        return userLoginName;
    }

    @Bean
    @UserPassword
    public String getUserLoginPassword() {
        return userLoginPassword;
    }

    @Bean
    @ServerHostName
    public String getServerHostName() {
        return serverHostName;
    }

    @Bean
    @JTapiExtNumber
    public String getJTapiExtNumber() {
        return jTapiExtNumber;
    }

    @Bean
    @JTapiPeer
    public String getJTapiPeer() {
        return jTapiPeer;
    }
}
