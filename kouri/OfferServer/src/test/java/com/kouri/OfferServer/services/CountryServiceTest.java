package com.kouri.OfferServer.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.kouri.OfferServer.domain.City;
import com.kouri.OfferServer.domain.CityAlreadyRegisteredException;
import com.kouri.OfferServer.domain.Country;
import com.kouri.OfferServer.domain.CountryRepository;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {
	@Mock
	private CountryRepository countryRepository;
	private static final String COUNTRY_NAME = "aCountry";
	private static final String CITY_NAME = "aCity";
	private static final String COUNTRY_PHONE_CODE = "aPhoneCode";
	@InjectMocks
	private CountryService countryService = new CountryService(countryRepository);
	private Country country;
	private List<Country> countries;
	private List<CountryDTO> countryDTOs;
	private CountryDTO countryDTO;
	private CityInputDTO cityInputDTO;

	@Before
	public void setUp() throws CityAlreadyRegisteredException {
		country = new Country(COUNTRY_NAME, COUNTRY_PHONE_CODE);
		countryDTOs = new ArrayList<CountryDTO>();
		countries = new ArrayList<Country>();
		countries.add(country);
		countryDTO = new CountryDTO(country);
		countryDTOs.add(countryDTO);
		cityInputDTO = new CityInputDTO(CITY_NAME, COUNTRY_NAME);
	}

	@Test
	public void oneCountryService_findAll_returnListOfCountryDTOs() {
		Mockito.when(countryRepository.findAll()).thenReturn(countries);
		assertEquals(countryDTOs, countryService.findAll());
	}

	@Test
	public void notRegisteredCountry_saveCountry_countryIsSaved() throws CountryAlreadyRegisteredException {
		Mockito.when(countryRepository.save(country)).thenReturn(country);
		Mockito.when(countryRepository.findByName(COUNTRY_NAME)).thenReturn(null);
		countryService.saveCountry(countryDTO);
		Mockito.verify(countryRepository, Mockito.times(1)).save(country);

	}

	@Test(expected = CountryAlreadyRegisteredException.class)
	public void notRegisteredCountry_saveCountry_throwCountryAlreadyRegisterException()
			throws CountryAlreadyRegisteredException {
		Mockito.when(countryRepository.save(country)).thenReturn(country);
		Mockito.when(countryRepository.findByName(COUNTRY_NAME)).thenReturn(country);
		countryService.saveCountry(countryDTO);
	}

	@Test(expected = CountryNotFoundException.class)
	public void countryNameNotFound_addCity_throwCountryNotFoundException()
			throws CountryNotFoundException, CityAlreadyRegisteredException {
		Mockito.when(countryRepository.findByName(COUNTRY_NAME)).thenReturn(null);
		countryService.addCity(cityInputDTO);
	}

	@Test
	public void inputCityDTO_addCity_CityIsAddedToCountry()
			throws CountryNotFoundException, CityAlreadyRegisteredException {
		Mockito.when(countryRepository.save(country)).thenReturn(country);
		Mockito.when(countryRepository.findByName(COUNTRY_NAME)).thenReturn(country);
		countryService.addCity(cityInputDTO);
		assertTrue(country.getCities().contains(new City(cityInputDTO.cityName)));
	}

	@Test(expected = CityAlreadyRegisteredException.class)
	public void cityAlreadyRegistered_addCity_throwCityAlreadyRegisteredException()
			throws CityAlreadyRegisteredException, CountryNotFoundException {
		country.addCity(new City(cityInputDTO.cityName));
		Mockito.when(countryRepository.findByName(COUNTRY_NAME)).thenReturn(country);
		countryService.addCity(cityInputDTO);

	}

	@Test
	public void inputCityDTO_addCity_CityIsSaved() throws CountryNotFoundException, CityAlreadyRegisteredException {
		Mockito.when(countryRepository.findByName(COUNTRY_NAME)).thenReturn(country);
		Mockito.when(countryRepository.save(country)).thenReturn(country);
		countryService.addCity(cityInputDTO);
		Mockito.verify(countryRepository, Mockito.times(1)).save(country);
	}

}
