package com.kouri.OfferServer.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class CountryTest {
	private static final String COUNTRY_NAME = "aCountry";
	private static final String COUNTRY_PHONE_CODE = "aPhoneCode";

	private List<City> cities;
	private Country country;

	@Before
	public void setUp() {
		cities = new ArrayList<City>();
		cities.add(new City());
		cities.add(new City());
		country = new Country(COUNTRY_NAME, COUNTRY_PHONE_CODE);
	}
}