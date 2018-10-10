package com.couchbase.oauth.server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecuredController {

    @PreAuthorize("#oauth2.hasScope('ROLE_USER')")
    @RequestMapping("/hello")
    public String hello() {
        return "Hello, this REST api is secured";
    }
}
