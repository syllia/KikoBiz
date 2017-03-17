package com.kouri.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SubCategoryTest {
	@Mock
	private Category category;
	private static final String NAME_SUBCATEGORY = "Jouet";
	private SubCategory subCategory;

	@Before
	public void setUp() {
		subCategory = new SubCategory(category, NAME_SUBCATEGORY);

	}

	@Test
	public void subcategory_getCategory_returnCategory() {
		assertEquals(subCategory.getCategory(), category);
	}

}
