package com.couchbase.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.couchbase.oauth.server.service.CouchbaseUserDetailsService;

@Configuration
@EnableWebMvc
@Order(-1) 
public class CouchbaseResourceServer extends WebSecurityConfigurerAdapter {

    @Autowired
    private CouchbaseUserDetailsService userDetailsService;   
	    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// @formatter:off
    	http.requestMatchers()
    		.antMatchers("/login", "/oauth/authorize")
			.and()
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.permitAll();   
	// @formatter:on
    }    
    
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        	auth
        		.userDetailsService(userDetailsService)
        		.passwordEncoder(encoder());
    }       

    @Bean
    public PasswordEncoder encoder(){
    	return new BCryptPasswordEncoder();
    }
    
}
