package com.example.securityhomework.configs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
@Profile("jdbc")
public class jdbcSecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(jdbcSecurityConfig.class.getName());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("JDBS Manager Auth");
        http.authorizeRequests()
                .antMatchers("/auth_page/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN","MAINADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
}
