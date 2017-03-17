package com.kouri.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.kouri.domain.Category;
import com.kouri.domain.CategoryRepository;
import com.kouri.domain.SubCategory;
import com.kouri.domain.SubCategoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class SubCategoryServiceTest {
	@Mock
	CategoryRepository categoryRepository;
	@Mock
	SubCategoryRepository subCategoryRepository;

	@InjectMocks
	private SubCategoryService subCategoryService = new SubCategoryService(subCategoryRepository, categoryRepository);
	private Category category;
	private SubCategory subCategory;
	private List<SubCategory> subCategories = new ArrayList<>();

	@Before
	public void setUp() {
		category = new Category(UUID.randomUUID(), "jojo");
		subCategory = new SubCategory(UUID.randomUUID(), category, "jut");
	}

	@Test
	public void newSubCategory_save_retrunSubCategory()
			throws ElementIsAlreadyAddedExecption, CategoryNotExistExecption {
		Mockito.when(categoryRepository.exists(category.getId())).thenReturn(true);
		Mockito.when(categoryRepository.getOne(category.getId())).thenReturn(category);
		Mockito.when(subCategoryRepository.findByNameWithIgnoreCase(subCategory.getName())).thenReturn(subCategories);
		Mockito.when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);

		assertEquals(subCategoryService.save(category.getId().toString(), subCategory.getName()), subCategory);
	}

	@Test(expected = CategoryNotExistExecption.class)
	public void newSubCategoryCategoryNotExist_save_throwCategoryNotExist()
			throws ElementIsAlreadyAddedExecption, CategoryNotExistExecption {
		Mockito.when(categoryRepository.exists(category.getId())).thenReturn(false);
		Mockito.when(categoryRepository.getOne(category.getId())).thenReturn(category);
		Mockito.when(subCategoryRepository.findByNameWithIgnoreCase(subCategory.getName())).thenReturn(subCategories);
		Mockito.when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);

		assertEquals(subCategoryService.save(category.getId().toString(), subCategory.getName()), subCategory);
	}

	@Test(expected = ElementIsAlreadyAddedExecption.class)
	public void subCategoryAlreadyExist_save_throwElementIsAlreadyAddedExist()
			throws ElementIsAlreadyAddedExecption, CategoryNotExistExecption {
		subCategories.add(subCategory);
		Mockito.when(categoryRepository.exists(category.getId())).thenReturn(true);
		Mockito.when(categoryRepository.getOne(category.getId())).thenReturn(category);
		Mockito.when(subCategoryRepository.findByNameWithIgnoreCase(subCategory.getName())).thenReturn(subCategories);
		Mockito.when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);

		assertEquals(subCategoryService.save(category.getId().toString(), subCategory.getName()), subCategory);
	}
}
