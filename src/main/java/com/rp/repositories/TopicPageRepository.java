package com.rp.repositories;

import com.rp.data.TopicPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicPageRepository extends BaseRepository<TopicPage, Integer> {
    @Query(value = "SELECT distinct tp FROM TopicPage tp left join fetch tp.author a left join fetch tp.topic t left join fetch t.category c left  join fetch tp.productOnTopics p",
            countQuery = "SELECT distinct count(tp) FROM TopicPage tp left join   tp.author a left join   tp.topic t left join   t.category c left  join   tp.productOnTopics p")
    Page<TopicPage> findAll(Pageable pageable);

    @Query(value = "SELECT distinct tp FROM TopicPage tp left join fetch tp.author a left join fetch tp.topic t left join fetch t.category c left  join fetch tp.productOnTopics p where a.id = ?1",
            countQuery = "SELECT distinct count(tp) FROM TopicPage tp left join  tp.author a left join  tp.topic t left join  t.category c left  join  tp.productOnTopics p where a.id = ?1")
    Page<TopicPage> findAllTopicPageByAuthorId(Integer id, Pageable pageable);

    Page<TopicPage> findTopicPageByTopicTitle(String topicTile, Pageable pageable);
}
