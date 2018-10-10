package com.couchbase.oauth.server.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import com.couchbase.oauth.server.model.CouchbaseClientDetails;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "oAuthClient")
public interface CouchbaseClientDetailsRepository extends CouchbasePagingAndSortingRepository<CouchbaseClientDetails, String> {

    CouchbaseClientDetails findByClientId(String clientId);
}
