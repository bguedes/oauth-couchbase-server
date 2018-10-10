package com.couchbase.oauth.server.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import com.couchbase.oauth.server.model.CouchbaseApproval;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "cbApproval")
public interface CouchbaseApprovalRepository extends CouchbasePagingAndSortingRepository<CouchbaseApproval, String> {

}
