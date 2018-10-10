package com.couchbase.oauth.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.couchbase.oauth.server.model.CouchbaseAccessToken;

@Repository
public interface AccessToken extends CrudRepository<CouchbaseAccessToken, String> {

}
