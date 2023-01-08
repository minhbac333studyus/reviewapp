package com.rp.controller;

import com.rp.data.Author;
import com.rp.services.AuthorService;
import com.rp.services.TopicPageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Random;

@RequestMapping("/authors")
@Controller
@Log4j2
@Transactional
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	@Autowired
	private TopicPageService topicPageService;

	@GetMapping("")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel<Author>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) { 
		Pageable paging = PageRequest.of(page, size);
		Page<Author> authorPage;
		try {
			authorPage = authorService.findAll(paging);
			if (authorPage.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<Author> authors = authorPage.getContent();
			ResponseModel<Author> res = new ResponseModel<>();
			res.setDataForResponse(authorPage, authors);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error("get All authors issue", () -> e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) {
		try {
			authorService.create(author);
			return new ResponseEntity<Author>(author, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Create authors issue {}", () -> author.toString());
			return new ResponseEntity<Author>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Author> updateAuthor(@PathVariable Integer id, @Valid @RequestBody Author author) {
		try {
			authorService.updateById(id, author);
			return new ResponseEntity<Author>(author, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Create authors issue {}", () -> author.toString());
			return new ResponseEntity<Author>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Author> getAuthorById(@PathVariable(value = "id") Integer id) {
		Author author = authorService.findbyId(id);
		try {
			return new ResponseEntity<Author>(author, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
	    log.info("delete author with id {}", () -> id);
	    try {
	        authorService.deleteById(id);
	        return new ResponseEntity<Void>(HttpStatus.OK);
	    } catch (Exception e) {
	        log.error("Delete author with id {} issue", () -> id);
	        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@PostMapping("/generate")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Author> generateRandomData() {
		try {
			  // Create a random number generator
		    Random rand = new Random();
		    
		    // Generate a random number between 0 and 999
		   
	
		    String[] firstNames = {"John", "Jane", "Sarah", "Mike", "Emily"};
		    
		    // Create an array of last names
		    String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones"};
		    
		    // Create a random number generator
		    Random rand1 = new Random();
		    Author a = null ;
		    // Generate a random index for the first and last name arrays
		  
		    for(int i = 0 ; i < 20; i ++) {
			    int num = rand.nextInt(1000);
			    // Generate a random email address
			    String email = "user" + num + "@gmail.com";
		    	  int firstNameIndex = rand1.nextInt(firstNames.length);
				    int lastNameIndex = rand1.nextInt(lastNames.length);
		    	  String firstName = firstNames[firstNameIndex];
				    String lastName = lastNames[lastNameIndex];
				    String name = firstName + " " + lastName;
				    String bio = "As a software engineer, I have a passion for creating and developing high-quality,\r\n"
				    		+ " efficient, and effective solutions to complex problems. With several years of experience in the field, \r\n"
				    		+ " I have a strong foundation in computer science principles and a proven track record of delivering successful projects.\r\n"
				    		+ " I am constantly learning and staying up-to-date with the latest technologies and methodologies to ensure that I am able to provide the best possible solutions for my clients. \r\n"
				    		+ " Whether working independently or as part of a team, I thrive on the challenge of solving problems and delivering results.";
					
				a= new Author(name,bio,email);
					 
					authorService.create(a);
				
		    }
		    // Generate a random name
			return new ResponseEntity<Author>(a, HttpStatus.CREATED);
			
		} catch (Exception e) { 
			return new ResponseEntity<Author>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}