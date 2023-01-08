package com.rp.repositories;

import com.rp.data.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends BaseRepository<Author, Integer> {
    Page<Author> findByFullNameContaining(String fullName, Pageable pageable);
}
