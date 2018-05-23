package ar.com.sebasira.eurekanamingserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    public static class WebSecurty extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("myuser").password("mypassword").roles("USER");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            /**
             * Needed to add and().csrf().disabled() otherwise clients will trow 403 when trying to autenticate
             *
             * Reference: https://github.com/spring-cloud/spring-cloud-netflix/issues/2754
             */
            http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable();
        }
    }
}
