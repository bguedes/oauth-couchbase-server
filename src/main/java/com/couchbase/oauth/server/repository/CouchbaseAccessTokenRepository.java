package com.couchbase.oauth.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import com.couchbase.oauth.server.model.CouchbaseAccessToken;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "couchbaseAccessToken")
public interface CouchbaseAccessTokenRepository extends CouchbasePagingAndSortingRepository<CouchbaseAccessToken, String> {

    List<CouchbaseAccessToken> findByClientId(String clientId);

    List<CouchbaseAccessToken> findByClientIdAndUsername(String clientId, String username);

    Optional<CouchbaseAccessToken> findByTokenId(String tokenId);

    Optional<CouchbaseAccessToken> findByRefreshToken(String refreshToken);

    Optional<CouchbaseAccessToken> findByAuthenticationId(String authenticationId);
}
