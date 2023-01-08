package com.rp.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rp.data.Category;
import com.rp.data.Category;
import com.rp.data.TopicPage;
import com.rp.repositories.CategoryRepository;
import com.rp.services.CategoryService;
import com.rp.services.TopicPageService;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/categories")
@Controller
@Log4j2
@Transactional
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel<Category>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) { 
		Pageable paging = PageRequest.of(page, size);
		Page<Category> categoryPage;
		try {
			categoryPage = categoryService.findAll(paging);
			if (categoryPage.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<Category> categories = categoryPage.getContent();
			ResponseModel<Category> res = new ResponseModel<>();
			res.setDataForResponse(categoryPage, categories);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error("get All categories issue", () -> e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
		log.info("create category{}", () -> category.toString());
		try {
			categoryService.create(category);
			return new ResponseEntity<Category>(category, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Create categorys issue {}", () -> category.toString());
			return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @Valid @RequestBody Category category) {
		log.info("update category{}", () -> category.toString());
		try {
			categoryService.updateById(id, category);
			return new ResponseEntity<Category>(category, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Create categorys issue {}", () -> category.toString());
			return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Integer id) {
		Category category = categoryService.findbyId(id);
		log.info("get category{}", () -> category.toString());
		try {
			return new ResponseEntity<Category>(category, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
	    log.info("delete category with id {}", () -> id);
	    try {
	        categoryService.deleteById(id);
	        return new ResponseEntity<Void>(HttpStatus.OK);
	    } catch (Exception e) {
	        log.error("Delete category with id {} issue", () -> id);
	        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


}