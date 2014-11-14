package demos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //        .anyRequest()
                .antMatchers(HttpMethod.DELETE, "/service/entries/**")
                .access("hasRole('ROLE_ADMIN')")
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
