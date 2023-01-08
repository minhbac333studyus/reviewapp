package com.rp.repositories;

import com.rp.data.CommentOnTopicPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentOnTopicRepository extends BaseRepository<CommentOnTopicPage, Integer> {
    @Query(value =       "SELECT distinct cmt        FROM CommentOnTopicPage cmt left join fetch cmt.topicPage tp left join fetch tp.productOnTopics p left join fetch tp.author a left join fetch tp.topic t left join fetch t.category c ",
    		countQuery = "SELECT distinct count(cmt) FROM CommentOnTopicPage cmt left join   cmt.topicPage tp left join   tp.productOnTopics p left join   tp.author a left join   tp.topic t left join  t.category c")
    public Page<CommentOnTopicPage> findAll(Pageable page);
}
