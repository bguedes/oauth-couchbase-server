package com.couchbase.oauth.server.model;

import org.codehaus.jackson.annotate.JsonIgnore;

public interface CouchbaseDocument {

    String getType();
    void setType(String type);

    @JsonIgnore 
    String getId();
    
    @JsonIgnore 
    void setId(String id);

    @JsonIgnore 
    long getCas();
    
    @JsonIgnore 
    void setCas(long cas);
}
