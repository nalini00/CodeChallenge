package com.codeChallenge.codechallenge.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.codeChallenge.codechallenge.model.Continent;
import com.codeChallenge.codechallenge.model.Country;
import com.codeChallenge.codechallenge.repository.ContinentRepository;
import com.mongodb.MongoClient;

/**
 * @author NALINI
 *
 */
@Service
public class ContinentServiceImpl implements ContinentService {
	private static final Logger logger = LoggerFactory
			.getLogger(ContinentServiceImpl.class);
	@Autowired
	ContinentRepository continentRepository;

	public String getData(String continent, String country) {
		MongoTemplate template = new MongoTemplate((new MongoClient(
				"localhost", 27017)), "test");
		if (!(continent == null)) {
			Continent continentObj = continentRepository
					.findByContinent(continent);

			// TODO Auto-generated method stub
			List<Country> countryList = new ArrayList<Country>();

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

}
