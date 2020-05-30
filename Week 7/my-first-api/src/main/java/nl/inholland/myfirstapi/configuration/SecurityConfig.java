package nl.inholland.myfirstapi.configuration;

import lombok.extern.java.Log;
import nl.inholland.myfirstapi.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Log
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Authorizing...");
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/stock/**").permitAll()
                .antMatchers(HttpMethod.POST, "/stock/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/stock/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/stock/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/pianos/**").permitAll()
                .antMatchers(HttpMethod.POST, "/pianos/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/pianos/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/pianos/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/stock", true)
                .permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

      /*
        In order for this to work, go to https://localhost:8443/api/login
       */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
