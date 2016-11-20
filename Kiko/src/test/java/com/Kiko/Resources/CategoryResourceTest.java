package com.Kiko.Resources;

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
import org.springframework.http.HttpStatus;

import com.Kiko.domain.Category;
import com.Kiko.domain.CategoryRepository;
import com.Kiko.domain.SubCategory;
import com.Kiko.domain.SubCategoryAlreadyRegisteredException;
import com.Kiko.resources.CategoryResource;
import com.Kiko.services.CategoryAlreadyRegisterException;
import com.Kiko.services.CategoryNotFounException;
import com.Kiko.services.CategoryService;
import com.Kiko.services.DTOs.CategoryDTO;
import com.Kiko.services.DTOs.SubCategoryInputDTO;

@RunWith(MockitoJUnitRunner.class)
public class CategoryResourceTest {
	private static CategoryRepository categoryRepository;
	private static final String CATEGORY_NAME = "C";
	@Mock
	private static CategoryService categoryService;

	@InjectMocks
	private CategoryResource categoryResource = new CategoryResource(categoryService);

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
	public void oneCategoryResource_findAll_returnListOfCategoriesDTO() {
		Mockito.when(categoryService.findAll()).thenReturn(categoryDTOs);
		assertEquals(categoryDTOs, categoryResource.findAll().getBody());
	}

	@Test
	public void oneCategoryResource_findAll_returnResponseOk() {
		Mockito.when(categoryService.findAll()).thenReturn(categoryDTOs);
		assertEquals(HttpStatus.OK, categoryResource.findAll().getStatusCode());
	}

	@Test
	public void aCategoryDTOWithExistingName_saveCategory_returnConflictResponse()
			throws CategoryAlreadyRegisterException {
		Mockito.doThrow(new CategoryAlreadyRegisterException()).when(categoryService).saveCategory(categoryDTO);
		assertEquals(HttpStatus.CONFLICT, categoryResource.saveCategory(categoryDTO).getStatusCode());
	}

	@Test
	public void aValidCategoryDTO_saveCategory_returnOKResponse() throws CategoryAlreadyRegisterException {
		Mockito.doNothing().when(categoryService).saveCategory(categoryDTO);
		assertEquals(HttpStatus.OK, categoryResource.saveCategory(categoryDTO).getStatusCode());
	}

	@Test
	public void aValidCategoryDTO_saveCategory_CategoryIsSaved() throws CategoryAlreadyRegisterException {
		Mockito.doNothing().when(categoryService).saveCategory(categoryDTO);
		categoryResource.saveCategory(categoryDTO);
		Mockito.verify(categoryService, Mockito.times(1)).saveCategory(categoryDTO);

	}

	@Test
	public void aValidSubCategoryDTO_addSubCategory_returnNotFoundResponse()
			throws CategoryNotFounException, SubCategoryAlreadyRegisteredException {
		Mockito.doThrow(new CategoryNotFounException()).when(categoryService).addSubCategory(subCategoryInputDTO);
		assertEquals(HttpStatus.NOT_FOUND, categoryResource.addSubCategory(subCategoryInputDTO).getStatusCode());
	}

	@Test
	public void aValidSubCategoryDTO_addSubCategory_returnConflictResponse()
			throws CategoryNotFounException, SubCategoryAlreadyRegisteredException {
		Mockito.doThrow(new SubCategoryAlreadyRegisteredException()).when(categoryService)
				.addSubCategory(subCategoryInputDTO);
		assertEquals(HttpStatus.CONFLICT, categoryResource.addSubCategory(subCategoryInputDTO).getStatusCode());
	}

	@Test
	public void aValidSubCategoryDTO_addSubCategory_returnOKResponse()
			throws CategoryAlreadyRegisterException, CategoryNotFounException, SubCategoryAlreadyRegisteredException {
		Mockito.doNothing().when(categoryService).addSubCategory(subCategoryInputDTO);
		assertEquals(HttpStatus.OK, categoryResource.addSubCategory(subCategoryInputDTO).getStatusCode());
	}

	@Test
	public void aValidCategoryDTO_addSubCategory_SubCategoryIsSaved()
			throws CategoryAlreadyRegisterException, CategoryNotFounException, SubCategoryAlreadyRegisteredException {
		Mockito.doNothing().when(categoryService).addSubCategory(subCategoryInputDTO);
		categoryResource.addSubCategory(subCategoryInputDTO);
		Mockito.verify(categoryService, Mockito.times(1)).addSubCategory(subCategoryInputDTO);

	}

}
