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

import com.rp.data.CommentOnTopic;
import com.rp.services.CommentOnTopicService;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
@RequestMapping("/commentOnTopics") 
@Controller
@Log4j2 
public class CommentOnTopicController {
	@Autowired
	private CommentOnTopicService commentOnTopicService;
	@GetMapping("/")
	public ResponseEntity<List<CommentOnTopic>> getAll() { 
		log.info("get all comment on topic");
		try {
			List<CommentOnTopic> commentOnTopics = commentOnTopicService.findAll();
			if (commentOnTopics.isEmpty()) {
				return new ResponseEntity<List<CommentOnTopic>>(HttpStatus.NO_CONTENT);
			} 
			return new ResponseEntity<List<CommentOnTopic>>(commentOnTopics, HttpStatus.OK);
		} catch (Exception e) {
			log.error("get All commentOnTopics issue" ,() -> e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/")
	public ResponseEntity<CommentOnTopic> createCommentOnTopic(@Valid @RequestBody CommentOnTopic commentOnTopic) {
		log.info("create comment on topic {}",()-> commentOnTopic.toString());
		try { 
			commentOnTopicService.create(commentOnTopic);
			return new ResponseEntity<CommentOnTopic>(commentOnTopic, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Create commentOnTopics issue {}",() -> commentOnTopic.toString());
			return new ResponseEntity<CommentOnTopic>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	@PutMapping("/{id}")
	public ResponseEntity<CommentOnTopic> updateCommentOnTopic(@PathVariable Integer id,@Valid @RequestBody CommentOnTopic commentOnTopic) {
		log.info("update comment on topic {}",()-> commentOnTopic.toString());
		try { 
			commentOnTopicService.updateById(id, commentOnTopic);
			return new ResponseEntity<CommentOnTopic>(commentOnTopic, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Create commentOnTopics issue {}",() -> commentOnTopic.toString());
			return new ResponseEntity<CommentOnTopic>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	} 
	@GetMapping("/{id}")
	public ResponseEntity<CommentOnTopic> getCommentOnTopicById(@PathVariable(value = "id") Integer id) {  
		CommentOnTopic commentOnTopic = commentOnTopicService.findbyId(id);
		log.info("get comment on topic {}",()-> commentOnTopic.toString());
		try  {
			return new ResponseEntity<CommentOnTopic>(commentOnTopic, HttpStatus.OK);
		} catch(Exception e) {
			log.error("Get commentOnTopics issue {}",() -> commentOnTopic.toString());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
