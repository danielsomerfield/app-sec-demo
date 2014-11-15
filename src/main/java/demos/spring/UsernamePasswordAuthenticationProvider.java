package demos.spring;

import demos.domain.AppUser;
import demos.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    final private UserService userService;

    public UsernamePasswordAuthenticationProvider(UserService userService) {

        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        assert supports(authentication.getClass());
        final UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        //TODO: principal and credientials could be null
        AppUser appUser = userService.findUserByUsernameAndPassword(token.getPrincipal().toString(), token.getCredentials().toString());
        if (appUser == null) {
            throw new BadCredentialsException("Could not authenticate");
        } else {
            return authentication;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
