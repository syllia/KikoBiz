package com.kouri.OfferServer.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class CategoryTest {
	private static final String CATEGORY_NAME = "A";
	private List<SubCategory> subcategories;
	private Category category;

	@Before
	public void setUp() {
		subcategories = new ArrayList<SubCategory>();
		subcategories.add(new SubCategory());
		subcategories.add(new SubCategory());
		category = new Category(CATEGORY_NAME);
	}

}
