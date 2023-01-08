package com.rp.repositories;

import com.rp.data.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Integer> {
    @Query(value = "select distinct c from Category c   left join fetch c.topics t",
            countQuery = "select distinct c from Category c   left join   c.topics t")
    public Page<Category> findAll( Pageable pageable);

    Optional<Category> findCategoryByName(String name);
}
