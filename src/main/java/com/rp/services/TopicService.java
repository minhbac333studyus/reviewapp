package com.rp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.data.TopicPage;
import com.rp.repositories.TopicPageRepository;

@Service
public class TopicService {
@Autowired
private TopicPageRepository topicPageRepository;
	public List<TopicPage> findAll() {
		// TODO Auto-generated method stub
		return topicPageRepository.findAll();
	}
	public TopicPage findbyId(int id) {
		return topicPageRepository.findById(id).get();
	}
	public TopicPage updateById(int id,TopicPage topicPage) {
		TopicPage topicPageDb = topicPageRepository.findById(id).get();
		topicPageDb = topicPage;
		topicPageDb.setId(id);
		return topicPageRepository.save(topicPageDb);
	}
	public TopicPage create(TopicPage topicPage) {
		return topicPageRepository.save(topicPage);
	}
	public List<TopicPage> getAlltopicPagesByAuthorId(Integer id){
		return topicPageRepository.findAllTopicPageByAuthorId(id);
	}
}


