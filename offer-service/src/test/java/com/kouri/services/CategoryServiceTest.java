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

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
	@Mock
	CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryService categoryService = new CategoryService(categoryRepository);
	private Category category;
	private List<Category> categories = new ArrayList<>();

	@Before
	public void setUp() {
		category = new Category(UUID.randomUUID(), "jojo");
	}

	@Test
	public void categoryService_findAll_returnAllCategories() throws ElementIsAlreadyAddedExecption {
		categories.add(category);
		Mockito.when(categoryRepository.findAll()).thenReturn(categories);
		assertEquals(categoryService.findAll(), categories);
	}

	@Test
	public void categoryService_findById_returnCategory() throws ElementIsAlreadyAddedExecption {
		Mockito.when(categoryRepository.getOne(category.getId())).thenReturn(category);
		assertEquals(categoryService.findById(category.getId().toString()), category);
	}
}
