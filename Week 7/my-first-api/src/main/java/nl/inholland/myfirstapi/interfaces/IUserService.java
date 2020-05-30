package nl.inholland.myfirstapi.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
    UserDetails loadUserByUsername(String username);
}
