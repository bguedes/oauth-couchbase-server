package com.couchbase.oauth.server.model;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.couchbase.client.java.repository.annotation.Id;
import com.couchbase.oauth.server.converter.SerializableObjectConverter;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document
@Data
@Getter
@Setter
public class CouchbaseRefreshToken implements OAuth2RefreshToken {

    @Id
    private String id;
    private String tokenId;
    private OAuth2RefreshToken token;
    private String authentication;

    public OAuth2Authentication getAuthentication() {
        return SerializableObjectConverter.deserialize(authentication);
    }

    public void setAuthentication(OAuth2Authentication authentication) {
        this.authentication = SerializableObjectConverter.serialize(authentication);
    }

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
}
