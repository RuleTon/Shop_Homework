package com.example.securityhomework.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;



@EnableWebSecurity
@Profile("inmemory")
public class InMemorySecurityConfig {


    private static final Logger logger = LoggerFactory.getLogger(InMemorySecurityConfig.class.getName());

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("")
                .roles("USER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }


    protected void configure(HttpSecurity http) throws Exception{
        logger.info("Memory Config Security");
        http.authorizeRequests()
                .anyRequest().permitAll()
                .antMatchers("/auth_page/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "MAINADMIN")
                .and()
                .formLogin();

    }

}
