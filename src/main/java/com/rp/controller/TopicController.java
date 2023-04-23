package com.rp.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

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
import com.rp.data.Topic;
import com.rp.data.TopicPage;
import com.rp.repositories.TopicRepository;
import com.rp.services.TopicService;
import com.rp.services.CategoryService;
import com.rp.services.TopicPageService;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
@RequestMapping("/topics") 
@Controller
@Log4j2
@Transactional
public class TopicController {

	@Autowired
	private TopicService topicService; 
	@Autowired
	private CategoryService categoryService; 
	@Autowired
	private TopicPageService topicPageService;
	
	@GetMapping("")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel<Topic>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) { 
		log.info("get all topic");
		Pageable paging = PageRequest.of(page, size);
		Page<Topic> topicsReturn;
		try {
			  topicsReturn = topicService.findAll(paging);
			log.debug("Size :"+ topicsReturn.getContent().size());
			if (topicsReturn.getContent().isEmpty()) {
				return new ResponseEntity<ResponseModel<Topic>>(HttpStatus.NO_CONTENT); 
			} 
			List<Topic> topics = topicsReturn.getContent();
			ResponseModel<Topic> res = new ResponseModel<>();
			res.setDataForResponse(topicsReturn, topics);
			return new ResponseEntity<>(res, HttpStatus.OK); 
		} catch (Exception e) {
			log.error("get All topics issue" ,() -> e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> createTopic(@Valid @RequestBody Topic topic) {
		log.info("create topic{}",()-> topic.toString());
		try { 
			topicService.save(topic);
			return new ResponseEntity<Topic>(topic, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Create topics issue {}",() -> topic.toString());
			return new ResponseEntity<Topic>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	@PutMapping("/{id}")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> updateTopic(@PathVariable Integer id,@Valid @RequestBody Topic topic) {
		log.info("update topic{}",()-> topic.toString());
		try { 
			topicService.updateById(id, topic);
			return new ResponseEntity<Topic>(topic, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Create topics issue {}",() -> topic.toString());
			return new ResponseEntity<Topic>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	} 
	@GetMapping("/{id}")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> getTopicById(@PathVariable(value = "id") Integer id) { 
		Topic topic = topicService.findbyId(id);
		log.info("get topic{}",()-> topic.toString());
		try  {
			return new ResponseEntity<Topic>(topic, HttpStatus.OK);
		} catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/generate")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> generateTopicData() {
	    try {
	        // Create a random number generator
	        Random rand = new Random();

	        // Create an array of topic names
	        String[] topicNames = {"Top 5 Skincare Products", "Best Vitamin C Serums", "Luxury Skincare Brands", "Natural Skincare Options", "Cruelty-Free Beauty Products"};

	        // Create a random number generator
	        Random rand1 = new Random();
	        Pageable paging = PageRequest.of(0, 10);
			Page<Category> categoryPage;
	        Topic t = null;
	        List<Category> cat = categoryService.findAll(paging).getContent();
	        
	        for(int i = 0; i < 20; i++) {
	            // Generate a random index for the topic names array
	            int topicNameIndex = rand1.nextInt(topicNames.length);
	            String topicName = topicNames[topicNameIndex];
	            int randomIndex = rand1.nextInt(3);
	            
	            // Generate a random description
	            String description = "Discover the " + topicName + " that you need in your skincare routine. From budget-friendly options to luxury brands, we have compiled a list of the best products to help you achieve glowing skin.";

	            t = new Topic(topicName, cat.get(randomIndex));

	            topicService.save(t);
	        }

	        return new ResponseEntity<Topic>(t, HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<Topic>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}

 
