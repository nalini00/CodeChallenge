package com.codeChallenge.codechallenge.controller;

import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeChallenge.codechallenge.dao.DBOperationImpl;
import com.codeChallenge.codechallenge.model.Continent;
import com.codeChallenge.codechallenge.model.Country;
import com.codeChallenge.codechallenge.repository.ContinentRepoImpl;
import com.codeChallenge.codechallenge.repository.ContinentRepository;
import com.codeChallenge.codechallenge.service.ContinentService;
import com.codeChallenge.codechallenge.service.ContinentServiceImpl;
import com.codeChallenge.codechallenge.service.JSONReadeService;

/**
 * @author NALINI
 *
 */
@RestController
public class ContinentController {

	private static final Logger logger = LoggerFactory
			.getLogger(ContinentController.class);

	private JSONReadeService service;

	ContinentService continentService;

	private ContinentRepository continentRepo;

	private ContinentRepoImpl repoImpl;

	private DBOperationImpl dbOp;
	@Autowired
	MongoTemplate template;

	@Autowired
	public ContinentController(JSONReadeService service,
			ContinentService continentService,
			ContinentRepository continentRepo, ContinentRepoImpl repoImpl,
			DBOperationImpl dbOp) {
		this.service = service;
		this.continentService = continentService;
		this.continentRepo = continentRepo;
		this.repoImpl = repoImpl;
		this.dbOp = dbOp;
	}

	/**
	 * This method is used to read the JSON content from continent.json file and
	 * store the data in Mongo collection Continents
	 * */
	@RequestMapping("/api/insert")
	public void getContinents() {
		logger.info("Reading JSON file and Inserting to db");
		JSONArray array = service.jsonReader();
		((ContinentRepoImpl) continentRepo).insertData(array);

	}

	/**
	 * 
	 * @return This method is used to get All continents Info
	 */
	@RequestMapping("/api/continents")
	public List<Continent> getAllContinents() {
		logger.info("Show All continents Info");
		return template.findAll(Continent.class);

	}

	/**
	 * @PathVariable("continent") user has to provide continet name This method
	 *                            is used to get aspecific continent Info which
	 *                            includes all countries info
	 * */

	@RequestMapping("/api/continents/{continent}")
	public Set<Country> getAllCountry(
			@PathVariable("continent") String continent) {

		logger.info("Show info   " + continent);

		return continentRepo.getCountryByContinent(continent);

	}

	/**
	 * 
	 * */

	/**
	 * @param country
	 * @return specific country Info
	 */
	@RequestMapping("/api/countries/{country}")
	public String getCountryInfo(@PathVariable("country") String country) {

		logger.info("Show Info country of    " + country);

		return continentRepo.getFlagByCountry(country);

	}

	/**
	 * @param continent
	 * @param country
	 * @return All continents Info
	 */
	@RequestMapping("/api/continents/{continent}/countries/{country}")
	public String getAllCountry(@PathVariable("continent") String continent,
			@PathVariable("country") String country) {
		logger.info("continent  " + continent);
		logger.info("country  " + country);
		return ((ContinentServiceImpl) continentService).getData(continent,
				country);

	}
}
