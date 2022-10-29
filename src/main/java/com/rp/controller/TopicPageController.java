package com.rp.controller;

import java.util.List;

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

import com.rp.data.TopicPage;
import com.rp.services.TopicPageService;

import lombok.extern.log4j.Log4j2;

@RequestMapping("/topicPage") 
@Controller
@Log4j2
public class TopicPageController {
	@Autowired
	private TopicPageService topicPageService;
	@GetMapping("/")
	public ResponseEntity<List<TopicPage>> getAll() { 
		log.info("get all topic page");
		try {
			List<TopicPage> topicPages = topicPageService.findAll();
			if (topicPages.isEmpty()) {
				return new ResponseEntity<List<TopicPage>>(HttpStatus.NO_CONTENT);
			} 
			return new ResponseEntity<List<TopicPage>>(topicPages, HttpStatus.OK);
		} catch (Exception e) {
			log.error("get All topicPages issue" ,() -> e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/")
	public ResponseEntity<TopicPage> createTopicPage(@Valid @RequestBody TopicPage topicPage) {
		log.info("create topic page {}",()-> topicPage.toString());
		try { 
			topicPageService.create(topicPage);
			return new ResponseEntity<TopicPage>(topicPage, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Create topicPages issue {}",() -> topicPage.toString());
			return new ResponseEntity<TopicPage>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	@PutMapping("/{id}")
	public ResponseEntity<TopicPage> updateTopicPage(@PathVariable Integer id,@Valid @RequestBody TopicPage topicPage) {
		log.info("update topic page {}",()-> topicPage.toString());
		try { 
			topicPageService.updateById(id, topicPage);
			return new ResponseEntity<TopicPage>(topicPage, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Create topicPages issue {}",() -> topicPage.toString());
			return new ResponseEntity<TopicPage>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	} 
	@GetMapping("/{id}")
	public ResponseEntity<TopicPage> getTopicPageById(@PathVariable(value = "id") Integer id) {  
		TopicPage topicPage = topicPageService.findbyId(id);
		log.info("get topic page {}",()-> topicPage.toString());
		try  {
			return new ResponseEntity<TopicPage>(topicPage, HttpStatus.OK);
		} catch(Exception e) {
			log.error("Get topicPages issue {}",() -> topicPage.toString());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
