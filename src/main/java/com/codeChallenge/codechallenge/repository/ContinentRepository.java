package com.codeChallenge.codechallenge.repository;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.codeChallenge.codechallenge.model.Continent;
import com.codeChallenge.codechallenge.model.Country;

/**
 * @author NALINI
 *
 * @param <T>
 */
public interface ContinentRepository<T> {
	
	public List<Continent> getAllObjects();
	
	public Set<Country> getCountryByContinent(String continent);
	
	public String getFlagByCountry(String country);

	public void saveObject(Continent object);
	
	public Continent findByContinent(String continent);

	

}



