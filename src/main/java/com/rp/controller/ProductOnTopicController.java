package com.rp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.rp.data.ProductOnTopic;
import com.rp.data.TopicPage;
import com.rp.repositories.ProductOnTopicRepository;
import com.rp.services.ProductOnTopicService;
import com.rp.services.TopicPageService;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
@RequestMapping("/productOnTopics") 
@Controller
@Log4j2
public class ProductOnTopicController {

	@Autowired
	private ProductOnTopicService productOnTopicService; 
	@GetMapping("/")
	public ResponseEntity<List<ProductOnTopic>> getAll() { 
		log.info("get all product on topic");
		try {
			List<ProductOnTopic> productOnTopics = productOnTopicService.findAll();
			if (productOnTopics.isEmpty()) {
				return new ResponseEntity<List<ProductOnTopic>>(HttpStatus.NO_CONTENT);
			} 
			return new ResponseEntity<List<ProductOnTopic>>(productOnTopics, HttpStatus.OK);
		} catch (Exception e) {
			log.error("get All productOnTopics issue" ,() -> e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/")
	public ResponseEntity<ProductOnTopic> createProductOnTopic(@Valid @RequestBody ProductOnTopic productOnTopic) {
		log.info("create product on topic {}",()-> productOnTopic.toString());
		try { 
			productOnTopicService.create(productOnTopic);
			return new ResponseEntity<ProductOnTopic>(productOnTopic, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Create productOnTopics issue {}",() -> productOnTopic.toString());
			return new ResponseEntity<ProductOnTopic>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	@PutMapping("/{id}")
	public ResponseEntity<ProductOnTopic> updateProductOnTopic(@PathVariable Integer id,@Valid @RequestBody ProductOnTopic productOnTopic) {
		log.info("update product on topic {}",()-> productOnTopic.toString());
		try { 
			productOnTopicService.updateById(id, productOnTopic);
			return new ResponseEntity<ProductOnTopic>(productOnTopic, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Create productOnTopics issue {}",() -> productOnTopic.toString());
			return new ResponseEntity<ProductOnTopic>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	} 
	@GetMapping("/{id}")
	public ResponseEntity<ProductOnTopic> getProductOnTopicById(@PathVariable(value = "id") Integer id) { 
		ProductOnTopic productOnTopic = productOnTopicService.findbyId(id);
		log.info("get product on topic {}",()-> productOnTopic.toString());
		try  {
			return new ResponseEntity<ProductOnTopic>(productOnTopic, HttpStatus.OK);
		} catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
 
}