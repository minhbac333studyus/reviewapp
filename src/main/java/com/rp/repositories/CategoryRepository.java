package com.rp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rp.data.Category;
@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer>{

}
