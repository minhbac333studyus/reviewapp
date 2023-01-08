package com.rp.services;

import com.rp.data.TopicPage;
import com.rp.repositories.TopicPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TopicPageService {
    @Autowired
    private TopicPageRepository topicPageRepository;

    public Page<TopicPage> findAll(Pageable page) {
        // TODO Auto-generated method stub
        return topicPageRepository.findAll(page);
    }

    public TopicPage findbyId(int id) {
        return topicPageRepository.findById(id).get();
    }

    public TopicPage updateById(int id, TopicPage topicPage) {
        TopicPage topicPageDb = topicPageRepository.findById(id).get();
        topicPageDb = topicPage;
        topicPageDb.setId(id);
        return topicPageRepository.save(topicPageDb);
    }

    public TopicPage create(TopicPage topicPage) {
        return topicPageRepository.save(topicPage);
    }

    public Page<TopicPage> findAlltopicPagesByAuthorId(Integer id, Pageable page) {
        return topicPageRepository.findAllTopicPageByAuthorId(id, page);
    }

    public Page<TopicPage> findAllTopicPageByTopicTilte(String title, Pageable page) {
        return topicPageRepository.findTopicPageByTopicTitle(title, page);
    }
}
