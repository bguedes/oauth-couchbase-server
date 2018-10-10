package com.couchbase.oauth.server.repository;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import com.couchbase.oauth.server.model.CouchbaseUserDetails;


@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "user")
public interface UserRepository extends CouchbasePagingAndSortingRepository<CouchbaseUserDetails, String> {

    List<CouchbaseUserDetails> findByUsername(String username);

    /*
    List<CouchbaseUserDetails> findByCompanyIdAndIsVisibleAndIsEnabledOrderByUsername(String companyId, 
    		boolean isVisible,
    		boolean isEnabled);
    */		

    //List<CouchbaseUserDetails> findByCompanyIdAndIsVisible(String companyId, boolean isVisible);
}
