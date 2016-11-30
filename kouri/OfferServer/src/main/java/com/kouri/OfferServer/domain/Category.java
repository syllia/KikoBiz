package com.kouri.OfferServer.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private List<SubCategory> subCategories;

	public Category(String p_name) {
		this.name = p_name;
		this.subCategories = new ArrayList<SubCategory>();
	}

	public String getName() {
		return name;
	}

	public List<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void addSubCategory(SubCategory subCategory) throws SubCategoryAlreadyRegisteredException {
		if (!hasCity(subCategory)) {
			subCategories.add(subCategory);
		} else {
			throw new SubCategoryAlreadyRegisteredException();
		}

	}

	private boolean hasCity(SubCategory subCategory) {
		for (SubCategory subCategoryRef : subCategories) {
			if (subCategory.equals(subCategoryRef)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Category) {
			Category category = (Category) object;
			return (this.name.equals(category.name));
		} else {
			return false;
		}
	}

}
