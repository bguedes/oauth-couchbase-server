package com.couchbase.oauth.server.model;

import java.time.LocalDateTime;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document
@Data
@Getter
@Setter
public class CouchbaseApproval {

    @Id
    private String id;
    @Field
    private String userId;
    @Field
    private String clientId;
    @Field
    private String scope;
    @Field
    private ApprovalStatus status;
    @Field
    private LocalDateTime expiresAt;
    @Field
    private LocalDateTime lastUpdatedAt;
}
