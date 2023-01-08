package com.rp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rp.data.Category;
import com.rp.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public Page<Category> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return categoryRepository.findAll(pageable);
	}

	public Category findbyId(int id) {
		return categoryRepository.findById(id).get();
	}

	public Category updateById(int id, Category category) {
		Category categoryDb = categoryRepository.findById(id).get();
		categoryDb = category;
		categoryDb.setId(id);
		return categoryRepository.save(categoryDb);
	}

	public Category create(Category category) {
		return categoryRepository.save(category);
	}
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}

}
