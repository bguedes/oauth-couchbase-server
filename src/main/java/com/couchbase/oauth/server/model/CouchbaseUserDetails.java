package com.couchbase.oauth.server.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document
@Data
@Getter
@Setter
public class CouchbaseUserDetails implements UserDetails, CredentialsContainer {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -856973164173468798L;

	@Id
    private String id;
	
	@Field
    private String username;
	@Field
    private String password;
    //private UUID userUUID;
	@Field
    private Collection<GrantedAuthority> authorities = Collections.emptySet();
	@Field
    private boolean accountNonExpired = true;
	@Field
    private boolean accountNonLocked = true;
	@Field
    private boolean credentialsNonExpired = true;
	@Field
    private boolean enabled = true;

	@Override
	public void eraseCredentials() {
		// TODO Auto-generated method stub
		
	}
}
