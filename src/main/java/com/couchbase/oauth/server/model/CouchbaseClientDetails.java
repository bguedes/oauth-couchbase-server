package com.couchbase.oauth.server.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document
@Data
@Getter
@Setter
public class CouchbaseClientDetails implements ClientDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6567871335341816994L;
	
	public static final String CLIENT_ID = "clientId";
	public static final String CLIENT_SECRET = "clientSecret";
	public static final String RESOURCE_IDS = "resourceIds";
	public static final String SCOPE = "scope";
	public static final String AUTHORIZED_GRANT_TYPES = "authorizedGrantTypes";
	public static final String REGISTERED_REDIRECT_URI = "registeredRedirectUri";
	public static final String AUTHORITIES = "authorities";
	public static final String ACCESS_TOKEN_VALIDITY_SECONDS = "accessTokenValiditySeconds";
	public static final String REFRESH_TOKEN_VALIDITY_SECONDS = "refreshTokenValiditySeconds";
	public static final String ADDITIONAL_INFORMATION = "additionalInformation";
	
    @Id
    private String id;

    @Field
    private String clientId;
    @Field
    private Set<String> resourceIds;
    @Field
    private boolean secretRequired;
    @Field
    private String clientSecret;
    @Field
    private boolean scoped;
    @Field
    private Set<String> scope;
    @Field
    private Set<String> authorizedGrantTypes;
    @Field
    private Set<String> registeredRedirectUri;
    @Field
    private Collection<GrantedAuthority> authorities = Collections.emptySet();
    @Field
    private Integer accessTokenValiditySeconds;
    @Field
    private Integer refreshTokenValiditySeconds;
    @Field
    private boolean autoApprove;
    @Field
    private Map<String, Object> additionalInformation;
    @Field
    private Set<String> autoApproveScopes;
    
	@Override
	public boolean isAutoApprove(String scope) {
		if (autoApproveScopes == null) {
			return false;
		}
		for (String auto : autoApproveScopes) {
			if (auto.equals("true") || scope.matches(auto)) {
				return true;
			}
		}
		return false;
	}
	
}
