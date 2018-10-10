package com.couchbase.oauth.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.couchbase.oauth.server.model.CouchbaseUserDetails;
import com.couchbase.oauth.server.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CouchbaseUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
        List<CouchbaseUserDetails> users = userRepository.findByUsername(userName);
        if(users.isEmpty()) {    
            return null;
        }

    	return users.get(0);
	}
}
