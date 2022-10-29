package com.rp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rp.data.ProductOnTopic;

public interface ProductOnTopicRepository  extends JpaRepository<ProductOnTopic, Integer>{

}
