package com.codeChallenge.codechallenge.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.codeChallenge.codechallenge.dao.DBOperationImpl;
import com.codeChallenge.codechallenge.model.Continent;
import com.codeChallenge.codechallenge.model.Country;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author NALINI
 *
 */
@Repository
public class ContinentRepoImpl implements ContinentRepository<Continent> {
	@Autowired
	public MongoClient mongo;
	public MongoDatabase database;
	@Autowired
	public MongoTemplate template;
	@Autowired
	DBOperationImpl application;
	private static final Logger logger = LoggerFactory
			.getLogger(ContinentRepoImpl.class);

	public void createConnection() {

		logger.info("Connected to the database successfully");

		// Accessing the database
		database = application.getDatabase();
		// template = getMongoTemplate();

		logger.info("Collection created successfully");

	}

	public void insertData(JSONArray array) {
		logger.info("Insude jsonarray  ");
		JSONObject json = null;
		Continent continentObj = null;
		Set<Country> countryList = null;
		Country countryObject = null;
		for (int i = 0; i < array.size(); i++) {
			continentObj = new Continent();
			json = (JSONObject) array.get(i);
			String continentName = json.get("continent").toString();
			logger.info("continentName   " + continentName);
			continentObj.setName(continentName);
			JSONArray countryArray = (JSONArray) json.get("countries");
			countryList = new HashSet<>();
			for (int j = 0; j < countryArray.size(); j++) {

				JSONObject countryJson = (JSONObject) countryArray.get(j);
				countryObject = new Country();
				logger.info("country details...........");
				String countryName = countryJson.get("name").toString();
				countryObject.setName(countryName);
				logger.info("Name  " + countryName);
				String countryFlag = countryJson.get("flag").toString();
				logger.info("flag  " + countryFlag);
				countryObject.setFlag(countryFlag);
				countryList.add(countryObject);

			}
			continentObj.setCountries(countryList);

			template.save(continentObj);
		}

	}

	@Override
	public List<Continent> getAllObjects() {
		return template.findAll(Continent.class);
	}

	@Override
	public Set<Country> getCountryByContinent(String continent) {

		List<Country> countryList = new ArrayList<Country>();
		List<Continent> list = template.findAll(Continent.class);
		Iterator<Continent> iterator = list.iterator();
		while (iterator.hasNext()) {
			Continent continentObj = iterator.next();
			if (continentObj.getName().toString().equals(continent)) {
				return continentObj.getCountries();
			}
		}
		return null;

	}

	@Override
	public void saveObject(Continent object) {
		template.save(object);
	}

	@Override
	public String getFlagByCountry(String country) {
		List<Country> countryList = new ArrayList<Country>();
		List<Continent> list = template.findAll(Continent.class);
		Iterator<Continent> iterator = list.iterator();
		while (iterator.hasNext()) {
			Continent continentObj = iterator.next();
			Set<Country> countryJson = continentObj.getCountries();
			Iterator<Country> countryIterator = countryJson.iterator();
			while (countryIterator.hasNext()) {
				Country countryObject = countryIterator.next();
				if (countryObject.getName().toString().equals(country)) {
					return countryObject.getFlag();
				}
			}

		}
		return null;

	}

	@Override
	public Continent findByContinent(String continent) {
		List<Country> countryList = new ArrayList<Country>();
		List<Continent> list = template.findAll(Continent.class);
		Iterator<Continent> iterator = list.iterator();
		while (iterator.hasNext()) {
			Continent continentObj = iterator.next();
			if (continent.equals(continentObj.getName())) {
				return continentObj;
			}

		}
		return null;
	}

}
