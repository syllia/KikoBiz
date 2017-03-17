package com.kouri.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class CityTest {
	@Mock
	private Country country;
	private static final String NAME_City = "J";
	private City city;

	@Before
	public void setUp() {
		city = new City(country, NAME_City);

	}

	@Test
	public void subcategory_getCategory_returnCategory() {
		assertEquals(city.getCountry(), country);
	}

}
