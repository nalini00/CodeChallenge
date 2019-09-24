package com.codeChallenge.codechallenge.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@Repository
public class DBOperationImpl {

	@Bean
	public MongoClient getMongoClient() {

		return new MongoClient("localhost", 27017);
	}

	@Bean
	public MongoDatabase getDatabase() {
		return getMongoClient().getDatabase("test");
	}

	@Bean
	public MongoTemplate getMongoTemplate() {

		return new MongoTemplate(getMongoClient(), "test");
	}

}
