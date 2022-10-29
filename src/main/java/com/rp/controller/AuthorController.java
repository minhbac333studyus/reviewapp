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
 
import com.rp.data.Author;
import com.rp.data.TopicPage;
import com.rp.repositories.AuthorRepository;
import com.rp.services.AuthorService;
import com.rp.services.TopicPageService;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
@RequestMapping("/authors") 
@Controller
@Log4j2
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	@Autowired
	private TopicPageService topicPageService;
	@GetMapping("/")
	public ResponseEntity<List<Author>> getAll() { 
		try {
			List<Author> authors = authorService.findAll();
			if (authors.isEmpty()) { 
				return new ResponseEntity<List<Author>>(HttpStatus.NO_CONTENT);
			} 
			return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
		} catch (Exception e) {
			log.error("get All authors issue" ,() -> e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/")
	public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) {
		try { 
			authorService.create(author);
			return new ResponseEntity<Author>(author, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Create authors issue {}",() -> author.toString());
			return new ResponseEntity<Author>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	@PutMapping("/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable Integer id,@Valid @RequestBody Author author) {
		try { 
			authorService.updateById(id, author);
			return new ResponseEntity<Author>(author, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Create authors issue {}",() -> author.toString());
			return new ResponseEntity<Author>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	} 
	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable(value = "id") Integer id) { 
		Author author = authorService.findbyId(id);
		try  {
			return new ResponseEntity<Author>(author, HttpStatus.OK);
		} catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/{id}/topicPages")
	public ResponseEntity<List<TopicPage>> getAllTopicPageByAuthorId(@PathVariable(value = "id") Integer id) { 
		List<TopicPage> topicPages= topicPageService.getAlltopicPagesByAuthorId(id);
		try  {
			return new ResponseEntity<List<TopicPage>>(topicPages, HttpStatus.OK);
		} catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}