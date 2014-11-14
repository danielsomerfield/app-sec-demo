package demos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    public SecurityConfig()
    {
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
        //auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    }

    private PasswordEncoder encoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {

                try {
                    MessageDigest m = MessageDigest.getInstance("MD5");
                    m.update(rawPassword.toString().getBytes());
                    return toHex(m.digest());
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException("Failed to get hash algorithm.");
                }
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }
        };
    }

    private static String toHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            if ((0xff & bytes[i]) < 0x10) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(0xff & bytes[i]));
        }
        return sb.toString();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //        .anyRequest()
                .antMatchers(HttpMethod.DELETE, "/service/entries/**")
                .authenticated()
                .and().formLogin()
                .successHandler((request, response, authentication) -> {
                    response.setStatus(SC_OK);
                    response.setContentType("application/json");
                    writeStatusMessage(response, "Login succeeded.");
                })
                .failureHandler((request, response, authentication) -> {
                    response.setStatus(SC_FORBIDDEN);
                    response.setContentType("application/json");
                    writeStatusMessage(response, "Login failed.");
                })
                .and().csrf().disable();
    }

    private void writeStatusMessage(HttpServletResponse response, String message)
            throws IOException {
        String json = String.format("{\"message\":\"%s\"}", message);
        response.getWriter().print(json);
    }
}
