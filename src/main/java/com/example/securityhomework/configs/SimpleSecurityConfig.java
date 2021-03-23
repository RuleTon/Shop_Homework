package com.example.securityhomework.configs;


import com.example.securityhomework.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug = true)
@Profile("dao")
public class SimpleSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(DaoSecurityConfig.class.getName());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Dao Provider Auth");
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "MAINADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }

}
