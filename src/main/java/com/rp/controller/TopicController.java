package com.rp.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces; 

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
 
import com.rp.data.Topic;
import com.rp.data.TopicPage;
import com.rp.repositories.TopicRepository;
import com.rp.services.TopicService;
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
	private TopicPageService topicPageService;
	
	@GetMapping("")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Topic>> getAll() { 
		log.info("get all topic");
		try {
			List<Topic> topics = topicService.findAll();
			log.debug("Size :"+ topics.size());
			if (topics.isEmpty()) {
				return new ResponseEntity<List<Topic>>(HttpStatus.NO_CONTENT);
			} 
			return new ResponseEntity<List<Topic>>(topics, HttpStatus.OK);
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
 
}