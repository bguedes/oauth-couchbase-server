package com.couchbase.oauth.server;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.couchbase.oauth.server.model.CouchbaseClientDetails;
import com.couchbase.oauth.server.model.CouchbaseUserDetails;
import com.couchbase.oauth.server.repository.CouchbaseClientDetailsRepository;
import com.couchbase.oauth.server.repository.UserRepository;

@Component
public class ClientCLR implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouchbaseClientDetailsRepository customClientDetailsRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        createOauthClients();
        createUser();
    }

    static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";

    private void createOauthClients() {

        CouchbaseClientDetails client = new CouchbaseClientDetails();
        client.setId("client::android::1");

        client.setResourceIds(new HashSet<>(Arrays.asList("resource_id")) );
        client.setClientId("android-client");
        client.setClientSecret(passwordEncoder.encode("android-secret"));
        client.setAuthorizedGrantTypes(new HashSet<>(Arrays.asList(AUTHORIZATION_CODE)));
        client.setScope(new HashSet<String>(Arrays.asList(SCOPE_READ, SCOPE_WRITE, TRUST)));
        client.setSecretRequired(true);
        client.setAccessTokenValiditySeconds(300);
        client.setRefreshTokenValiditySeconds(300);
        client.setScoped(false);
        client.setAutoApprove(false);
        client.setRegisteredRedirectUri(new HashSet<String>(Arrays.asList("http://localhost:9000/callback")));
        
        customClientDetailsRepository.save(client);

        CouchbaseClientDetails client2 = new CouchbaseClientDetails();
        client2.setResourceIds(new HashSet<>(Arrays.asList("resource_id", "resource_id2", "resource_id3")) );
        client2.setId("client::desktop::1");
        client2.setClientId("desktop");
        client2.setClientSecret(passwordEncoder.encode("password"));
        client2.setAuthorizedGrantTypes(new HashSet<>(Arrays.asList(AUTHORIZATION_CODE, REFRESH_TOKEN)));
        client2.setScope(new HashSet<String>(Arrays.asList(SCOPE_READ, SCOPE_WRITE, TRUST)));
        client2.setSecretRequired(true);
        client2.setAccessTokenValiditySeconds(300);
        client2.setRefreshTokenValiditySeconds(300);
        client2.setScoped(false);
        client2.setAutoApprove(false);
        client2.setRegisteredRedirectUri(new HashSet<String>(Arrays.asList("http://localhost:9000/callback")));
        
        customClientDetailsRepository.save(client2);
    }

    public void createUser(){
    	CouchbaseUserDetails user = new CouchbaseUserDetails();
        user.setId("user::1");
        user.setUsername("foo");
        user.setPassword(passwordEncoder.encode("password"));
        user.setEnabled(true);
        user.setAuthorities(new HashSet<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        userRepository.save(user);
    }

}
