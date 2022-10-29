package com.rp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rp.data.Topic;
@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>{

}
