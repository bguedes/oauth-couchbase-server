package com.couchbase.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import com.couchbase.oauth.server.service.CouchbaseClientDetailsService;
import com.couchbase.oauth.server.service.CouchbaseToken;

@Configuration
@EnableAuthorizationServer
public class CouchbaseAuthorizationServer extends AuthorizationServerConfigurerAdapter {
	
    public static final String RESOURCE_ID = "couchbaseSSO"; 
	
	static final String CLIEN_ID = "android-client";
	static final String CLIENT_SECRET = "android-secret";
	static final String GRANT_TYPE_PASSWORD = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
	static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

	@Autowired
	private CouchbaseClientDetailsService couchbaseClientDetailsService;

	@Autowired
	private CouchbaseToken tokenStore;		

	@Override
	public void configure(final ClientDetailsServiceConfigurer clientsDetailsConfigurer) throws Exception {
		clientsDetailsConfigurer.withClientDetails(couchbaseClientDetailsService);
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// @formatter:off
		endpoints.tokenStore(tokenStore);
		// @formatter:on
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

}
