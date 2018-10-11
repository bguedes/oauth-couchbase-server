# OAuth implementation with Couchbase Server

This is an implementation of OAuth2 using Couchbase as a repository and Spring Boot OAuth2 implementation.

The grant type used is the authorize code.

## How to use it

### Pre-requistes

Java installed ((build 1.8.0_181-b13 tested)
Maven installed (Apache Maven 3.5.3 tested)

### Step 1
Install and create Couchbase Server (tested version 5.5.1) 

create a bucket 'sso'

create a user called 'sso' give the 'password' value for the password

### Step 2
Change the application.properties file in regards your infrastructure. 
Default parameters for a single Couchbase server instance locally installed

```
spring.couchbase.bootstrap-hosts=127.0.0.1
spring.couchbase.bucket.name=sso
spring.couchbase.bucket.password=password
spring.data.couchbase.auto-index=true  
```

## Creating users manually in Couchbase

Insert using the Couchbase administration UI this document :

user::1

```javascript
{
  "password": "$2a$10$jz3YUk.8gFlDJVaI40mloeiCfJWYu1u/eGcbyeqvrDYkDmWyK8esq",
  "credentialsNonExpired": true,
  "accountNonExpired": true,
  "_class": "com.couchbase.oauth.server.model.CouchbaseUserDetails",
  "authorities": [
    {
      "role": "ROLE_USER",
      "_class": "org.springframework.security.core.authority.SimpleGrantedAuthority"
    }
  ],
  "enabled": true,
  "username": "brian",
  "accountNonLocked": true
}
```

user 		: brian
password 	: password

## Creating client manually in Couchbase

client::desktop::myclient

```javascript
{
  "authorizedGrantTypes": [
    "refresh_token",
    "authorization_code"
  ],
  "clientId": "myclient",
  "registeredRedirectUri": [
    "http://localhost:9000/callback"
  ],
  "secretRequired": true,
  "refreshTokenValiditySeconds": 300,
  "authorities": [],
  "autoApprove": false,
  "scoped": false,
  "scope": [
    "trust",
    "read",
    "write"
  ],
  "accessTokenValiditySeconds": 300,
  "clientSecret": "$2a$10$VW4Gob/x4lrLQh4a7H6lAelkfulwK8ymhvp92AwbxIczbJWx2fYui",
  "_class": "com.couchbase.oauth.server.model.CouchbaseClientDetails",
  "resourceIds": [
    "resource_id",
    "resource_id3",
    "resource_id2"
  ]
}
```
clientId 	 : desktop
clientSecret : password

For testing without a client OAuth application :

Authentification and accept ressources access 

```
http://localhost:8080/oauth/authorize?client_id=desktop&redirect_uri=http://localhost:9000/callback&response_type=code&scope=read
```

You will have this url in result :

```
http://localhost:9000/callback?code=RuIR16
```

This code value has to be kept and used in the next curl instruction to get a valid tokens

Get a token

```
curl -X POST --user desktop:password http://localhost:8080/oauth/token -H "content-type: application/x-www-form-urlencoded" -d "code=RuIR16&grant_type=authorization_code&redirect_uri=http://localhost:9000/callback&scope=read"

{"access_token":"fc8d57f9-cdea-4626-8598-4ed72baae558","token_type":"bearer","refresh_token":"08ebfefb-3cc1-4396-81b4-c23429bab565","expires_in":119,"scope":"read"}     
```

Use the given Token for accessing secured ressource

```
curl -X GET http://localhost:8080/secure/hello -H "authorization: Bearer fc8d57f9-cdea-4626-8598-4ed72baae558"
```

You should see in the console this message :


Hello, this REST api is secured  
