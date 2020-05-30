package nl.inholland.myfirstapi.configuration;

import lombok.Getter;
import lombok.Setter;
import nl.inholland.myfirstapi.models.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="activedirectory")
public class UserConfig {
    private List<User> users = new ArrayList<>();
}
