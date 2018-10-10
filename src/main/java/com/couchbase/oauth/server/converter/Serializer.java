package com.couchbase.oauth.server.converter;

import java.io.IOException;
import java.util.Optional;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.oauth.server.model.CouchbaseDocument;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Serializer<T extends CouchbaseDocument> {

	  private final ObjectMapper mapper;
	  private final Class<T> typeClass;
	  private final String bucketName;

	  public Serializer(ObjectMapper mapper, Class<T> typeClass, String bucketName) {
	    this.mapper = mapper;
	    this.typeClass = typeClass;
	    this.bucketName = bucketName;
	  }

	  public Optional<String> seralize(Object value) {
	    try {
	      return Optional.ofNullable(mapper.writeValueAsString(value));
	    } catch (JsonProcessingException e) {
	      log.info("Failed to serialize object {}", value, e);
	      return Optional.empty();
	    }
	  }

	  public Optional<T> deserialize(JsonDocument doc) {
	    try {
	      Optional<String> json = Optional.ofNullable(doc.content().toString());

	      if (json.isPresent()) {
	        T obj = mapper.readValue(json.get(), typeClass);
	        obj.setCas(doc.cas());
	        obj.setId(doc.id());
	        return Optional.of(obj);
	      }

	    } catch (IOException e) {
	      log.info("Failed to deserialize document {}", doc, e);
	    }

	    return Optional.empty();
	  }

	  public Optional<T> deserialize(JsonObject jsonObject) {
	    try {
	      Optional<String> json = Optional.ofNullable(jsonObject.get(bucketName).toString());

	      if (json.isPresent()) {
	        T obj = mapper.readValue(json.get(), typeClass);
	        obj.setId(jsonObject.getString("id"));
	        obj.setCas(jsonObject.getLong("cas"));
	        return Optional.of(obj);
	      }

	    } catch (IOException e) {
	      log.info("Failed to deserialize JSON object {}", jsonObject, e);
	    }

	    return Optional.empty();
	  }
	}
