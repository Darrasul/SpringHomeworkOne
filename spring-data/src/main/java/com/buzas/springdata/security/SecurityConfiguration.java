package com.buzas.springdata.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public void authConfig(
            AuthenticationManagerBuilder authBuilder, PasswordEncoder encoder, UserDetailsServiceImpl userDetailsService
    ) throws Exception {
        authBuilder.inMemoryAuthentication()
                .withUser("Vito")
                .password(encoder.encode("pass"))
                .roles("ADMIN")
                .and()
                .withUser("quest")
                .password(encoder.encode("quest"))
                .roles("QUEST");

        authBuilder.userDetailsService(userDetailsService);
    }
}
