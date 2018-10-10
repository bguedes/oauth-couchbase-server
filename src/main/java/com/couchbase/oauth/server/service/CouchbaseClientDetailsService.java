package com.couchbase.oauth.server.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.couchbase.oauth.server.model.CouchbaseClientDetails;
import com.couchbase.oauth.server.repository.CouchbaseClientDetailsRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CouchbaseClientDetailsService implements ClientDetailsService {

	@Autowired
	private CouchbaseClientDetailsRepository clientDetailsRepository;
	
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        CouchbaseClientDetails clientDetails = clientDetailsRepository.findByClientId(clientId);
        
        if (clientDetails == null) {
            throw new ClientRegistrationException(String.format("Client with id %s not found", clientId));
        }
        
        String resourceIds = clientDetails.getResourceIds()
        		.stream()
        		.collect(Collectors.joining(","));
        
        String scopes = clientDetails.getScope()
        		.stream()
        		.collect(Collectors.joining(","));
        
        String grantTypes = clientDetails.getAuthorizedGrantTypes()
        		.stream()
        		.collect(Collectors.joining(","));
        
        String authorities = clientDetails.getAuthorities()
        		.stream()
        		.map(authoritie -> authoritie.getAuthority())
        		.collect(Collectors.joining(","));

        String redirectUris = clientDetails.getRegisteredRedirectUri()
        		.stream()
        		.collect(Collectors.joining(","));
        		
        
        BaseClientDetails base = new BaseClientDetails(clientDetails.getClientId(), 
        		resourceIds, 
        		scopes, 
        		grantTypes, 
        		authorities, 
        		redirectUris);
        
        base.setClientSecret(clientDetails.getClientSecret());
        base.setAccessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds());
        base.setRefreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds());
        if(clientDetails.getAdditionalInformation() != null) {
            base.setAdditionalInformation(clientDetails.getAdditionalInformation());
        }
        base.setScope(clientDetails.getScope());
        
        return base;
    }
}
