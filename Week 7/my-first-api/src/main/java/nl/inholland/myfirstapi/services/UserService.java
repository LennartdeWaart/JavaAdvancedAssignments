package nl.inholland.myfirstapi.services;

import nl.inholland.myfirstapi.dao.UserRepository;
import nl.inholland.myfirstapi.interfaces.IUserService;
import nl.inholland.myfirstapi.models.SecurityUserDetails;
import nl.inholland.myfirstapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByUsername(username)).orElseThrow(
                () -> new UsernameNotFoundException("User " + username + " not found"));
        return new SecurityUserDetails(user);
    }
}
