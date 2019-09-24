package com.codeChallenge.codechallenge.service;

import java.io.File;
import java.nio.file.Files;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 * @author NALINI
 *
 */
@Service()
public class JSONReaderServiceImpl implements JSONReadeService {

	private static final Logger logger = LoggerFactory
			.getLogger(JSONReaderServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.codeChallenge.codechallenge.service.JSONReadeService#jsonReader()
	 */
	public JSONArray jsonReader() {
		logger.info("JSON Reader................");
		String fileName = "src/main/resources/continents.json";

		File file;
		try {
			file = ResourceUtils.getFile("classes\\config\\continents.json");
			// file = ResourceUtils.getFile(fileName);

			// File is found
			logger.info("File Found : " + file.exists());

			// Read File Content

			String content = new String(new String(Files.readAllBytes(file
					.toPath())));

			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(content);
			logger.info("JSON sent.........");
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
