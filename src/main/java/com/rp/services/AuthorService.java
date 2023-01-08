package com.rp.services;

import com.rp.data.Author;
import com.rp.repositories.AuthorRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Page<Author> findAll(Pageable page) {
        // TODO Auto-generated method stub
        Page<Author> authors = authorRepository.findAll(page);
        log.debug("authors size:" + authors.getNumberOfElements());
        return authors;
    }
    public int count()
    {
    	return (int) authorRepository.count();
    }
    public Author findbyId(int id) {
        return authorRepository.findById(id).get();
    }

    public Author updateById(int id, Author author) {
        Author authorDb = authorRepository.findById(id).get();
        authorDb = author;
        authorDb.setId(id);
        return authorRepository.save(authorDb);
    }
    
    public void deleteById(int id) {
    	authorRepository.deleteById(id);
    }
    public Author create(Author author) {
        return authorRepository.save(author);
    }

}
