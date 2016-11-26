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

import com.kouri.OfferServer.domain.Category;
import com.kouri.OfferServer.domain.CategoryRepository;
import com.kouri.OfferServer.domain.SubCategory;
import com.kouri.OfferServer.domain.SubCategoryAlreadyRegisteredException;
import com.kouri.OfferServer.services.DTOs.CategoryDTO;
import com.kouri.OfferServer.services.DTOs.SubCategoryInputDTO;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
	@Mock
	private static CategoryRepository categoryRepository;
	private static final String CATEGORY_NAME = "C";
	@InjectMocks
	private CategoryService categoryService = new CategoryService(categoryRepository);
	private Category category;
	private List<Category> categories;
	private List<CategoryDTO> categoryDTOs;
	private CategoryDTO categoryDTO;
	private SubCategoryInputDTO subCategoryInputDTO;

	@Before
	public void setUp() throws SubCategoryAlreadyRegisteredException {
		category = new Category(CATEGORY_NAME);
		categoryDTOs = new ArrayList<CategoryDTO>();
		category.addSubCategory(new SubCategory("A"));
		categories = new ArrayList<Category>();
		categories.add(category);
		categoryDTO = new CategoryDTO(category);
		categoryDTOs.add(categoryDTO);
		subCategoryInputDTO = new SubCategoryInputDTO("subCategoryName", CATEGORY_NAME);

	}

	@Test
	public void oneCategoryService_findAll_returnListOfCategoriesDTO() {
		Mockito.when(categoryRepository.findAll()).thenReturn(categories);
		assertEquals(categoryDTOs, categoryService.findAll());
	}

	@Test
	public void notRegisteredCategory_saveCategory_categoryIsSaved()
			throws CategoryAlreadyRegisterException, SubCategoryAlreadyRegisteredException {
		Mockito.when(categoryRepository.save(category)).thenReturn(category);
		Mockito.when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(null);
		categoryService.saveCategory(categoryDTO);
		Mockito.verify(categoryRepository, Mockito.times(1)).save(category);

	}

	@Test(expected = CategoryAlreadyRegisterException.class)
	public void notRegisteredCategory_saveCategory_categorySaved_throwCategoryAlreadyRegisterException()
			throws CategoryAlreadyRegisterException, SubCategoryAlreadyRegisteredException {
		Mockito.when(categoryRepository.save(category)).thenReturn(category);
		Mockito.when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(category);
		categoryService.saveCategory(categoryDTO);
	}

	@Test(expected = CategoryNotFounException.class)
	public void categoryNameNotFound_addSubCategory_throwCategoryNotFoundException()
			throws CategoryNotFounException, SubCategoryAlreadyRegisteredException {
		Mockito.when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(null);
		categoryService.addSubCategory(subCategoryInputDTO);
	}

	@Test
	public void inputSubCategoryDTO_addSubCategory_SubCategoryIsAddedToCategory()
			throws CategoryNotFounException, SubCategoryAlreadyRegisteredException {
		Mockito.when(categoryRepository.save(category)).thenReturn(category);
		Mockito.when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(category);
		categoryService.addSubCategory(subCategoryInputDTO);
		assertTrue(category.getSubCategories().contains(new SubCategory(subCategoryInputDTO.subCategoryName)));
	}

	@Test(expected = SubCategoryAlreadyRegisteredException.class)
	public void subCategoryAlreadyRegistered_addSubCategory_throwSubCategoryAlreadyRegisteredException()
			throws CategoryNotFounException, SubCategoryAlreadyRegisteredException {
		category.addSubCategory(new SubCategory(subCategoryInputDTO.subCategoryName));
		Mockito.when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(category);
		categoryService.addSubCategory(subCategoryInputDTO);

	}

	@Test
	public void inputSubCategoryDTO_addSubCategory_SubCategoryIsSaved()
			throws CategoryNotFounException, SubCategoryAlreadyRegisteredException {
		Mockito.when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(category);
		Mockito.when(categoryRepository.save(category)).thenReturn(category);
		categoryService.addSubCategory(subCategoryInputDTO);
		Mockito.verify(categoryRepository, Mockito.times(1)).save(category);
	}

}
