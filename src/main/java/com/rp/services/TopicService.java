package com.rp.services;

import com.rp.data.Category;
import com.rp.data.Topic;
import com.rp.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
//
//    public List<Topic> findAll() {
//        // TODO Auto-generated method stub
//        return topicRepository.findAll();
//    }
	public Page<Topic> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return topicRepository.findAll(pageable);
	}

    public Topic findbyId(int id) {
        return topicRepository.findById(id).get();
    }

    public Topic updateById(int id, Topic topic) {
        Topic topicDb = topicRepository.findById(id).get();
        topicDb = topic;
        topicDb.setId(id);
        return topicRepository.save(topicDb);
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public void delete(Topic topic) {
        topicRepository.delete(topic);
    }
}
