package com.couchbase.oauth.server.repository;

import java.util.Optional;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import com.couchbase.oauth.server.model.CouchbaseRefreshToken;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "couchbaseRefreshToken")
public interface CouchbaseRefreshTokenRepository extends CouchbasePagingAndSortingRepository<CouchbaseRefreshToken, String> {

    Optional<CouchbaseRefreshToken> findByTokenId(String tokenId);
}
