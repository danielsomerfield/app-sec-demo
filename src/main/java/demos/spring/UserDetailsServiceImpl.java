package demos.spring;

import demos.domain.AppUser;
import demos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static java.lang.String.*;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userService.getUser(username);
        if (user != null) {
            return new User(user.getUsername(), user.getPasswordHash(), Collections.<GrantedAuthority>emptyList());
        } else {
            throw new UsernameNotFoundException(format("Failed to find user %s", username));
        }
    }
}
