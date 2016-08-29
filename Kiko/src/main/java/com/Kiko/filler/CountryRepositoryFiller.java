package com.Kiko.filler;

import org.springframework.beans.factory.annotation.Autowired;

import com.Kiko.repositories.CountryRepository;

public class CountryRepositoryFiller {
	@Autowired
	public CountryRepositoryFiller(CountryRepository countryRepository) {
	}
}
