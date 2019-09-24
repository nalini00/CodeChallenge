package com.codeChallenge.codechallenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codeChallenge.codechallenge.repository.ContinentRepoImpl;

/**
 * @author NALINI
 *
 */
@SpringBootApplication
public class CodechallengeApplication {

	private static final Logger logger = LoggerFactory
			.getLogger(CodechallengeApplication.class);

	private static ContinentRepoImpl repo;

	@Autowired
	public CodechallengeApplication(ContinentRepoImpl repo) {
		this.repo = repo;
	}

	public static void main(String[] args) {

		SpringApplication.run(CodechallengeApplication.class, args);
		logger.info("Application Inetialization started..");
		repo.createConnection();
		logger.info("MongoDB connection successfully created... ");
	}

}
