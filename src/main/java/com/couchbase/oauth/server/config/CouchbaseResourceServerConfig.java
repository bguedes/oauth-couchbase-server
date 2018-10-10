package com.couchbase.oauth.server.config;

import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

public class CouchbaseResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "couchbaseOauth";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }
}
