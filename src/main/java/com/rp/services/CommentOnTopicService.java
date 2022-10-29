package com.rp.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.data.CommentOnTopic; 
import com.rp.repositories.CommentOnTopicRepository;

@Service
public class CommentOnTopicService {
	@Autowired
	private CommentOnTopicRepository commentOnTopicRepository;

	public List<CommentOnTopic> findAll() {
		// TODO Auto-generated method stub
		return commentOnTopicRepository.findAll();
	}

	public CommentOnTopic create(@Valid CommentOnTopic commentOnTopic) {
		// TODO Auto-generated method stub
		commentOnTopicRepository.save(commentOnTopic);
		return commentOnTopic;
	}

	public CommentOnTopic updateById(Integer id, @Valid CommentOnTopic commentOnTopic) {
		// TODO Auto-generated method stub
		CommentOnTopic commentOnTopicDb = commentOnTopicRepository.findById(id).get();
		commentOnTopicDb = commentOnTopic;
		commentOnTopicDb.setId(id);
		return commentOnTopicRepository.save(commentOnTopicDb);
	}

	public CommentOnTopic findbyId(Integer id) {
		// TODO Auto-generated method stub
		return commentOnTopicRepository.findById(id).get();
	}

}
