package com.rp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rp.data.TopicPage;
@Repository
public interface TopicPageRepository extends JpaRepository<TopicPage, Integer > {
	List<TopicPage> findAllTopicPageByAuthorId(Integer id);
}
