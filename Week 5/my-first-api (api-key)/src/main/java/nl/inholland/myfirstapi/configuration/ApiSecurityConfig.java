package nl.inholland.myfirstapi.configuration;

import lombok.extern.java.Log;
import nl.inholland.myfirstapi.dao.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Log
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    private final ApiKeyRepository apiKeyRepository;

    public ApiSecurityConfig(ApiKeyRepository apiKeyRepository) {
        log.info("Application secured.");
        this.apiKeyRepository = apiKeyRepository;
    }

    @Value("${api.token.header-name}")
    private String headerName;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(headerName);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();

            if (apiKeyRepository.findById(principal).isEmpty()) {
                throw new BadCredentialsException("API Key was not found in the system.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        httpSecurity
                .antMatcher("/**")
                .csrf().disable()  // disable X-site forgery
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // every new request needs authentication
                .and()
                .addFilter(filter) //  Check for the correct header value
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
