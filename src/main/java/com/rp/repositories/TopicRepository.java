package com.rp.repositories;

import com.rp.data.Category;
import com.rp.data.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends BaseRepository<Topic, Integer> {
    @Query(
    		value 	   = "select distinct t from Topic t left join fetch  t.category c",
            countQuery = "select distinct count(t) from Topic t left join t.category c")
    public Page<Topic> findAll( Pageable pageable);

}
