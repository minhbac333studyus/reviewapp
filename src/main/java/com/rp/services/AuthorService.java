package com.rp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.data.Author;
import com.rp.data.TopicPage;
import com.rp.repositories.AuthorRepository;

@Service
public class AuthorService {
@Autowired
private AuthorRepository authorRepository;
	public List<Author> findAll() {
		// TODO Auto-generated method stub
		return authorRepository.findAll();
	}
	public Author findbyId(int id) {
		return authorRepository.findById(id).get();
	}
	public Author updateById(int id,Author author) {
		Author authorDb = authorRepository.findById(id).get();
		authorDb = author;
		authorDb.setId(id);
		return authorRepository.save(authorDb);
	}
	public Author create(Author author) {
		return authorRepository.save(author);
	}

}
