package com.rp.services;

import com.rp.data.CommentOnTopicPage;
import com.rp.repositories.CommentOnTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class CommentOnTopicService {
	@Autowired
	private CommentOnTopicRepository commentOnTopicRepository;

	public Page<CommentOnTopicPage> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return commentOnTopicRepository.findAll(page);
	}
		public CommentOnTopicPage create (CommentOnTopicPage commentOnTopic){
			// TODO Auto-generated method stub
			commentOnTopicRepository.save(commentOnTopic);
			return commentOnTopic;
		}

		public CommentOnTopicPage updateById (Integer id, @Valid CommentOnTopicPage commentOnTopic){
			// TODO Auto-generated method stub
			CommentOnTopicPage commentOnTopicDb = commentOnTopicRepository.findById(id).get();
			commentOnTopicDb = commentOnTopic;
			commentOnTopicDb.setId(id);
			return commentOnTopicRepository.save(commentOnTopicDb);
		}

		public CommentOnTopicPage findbyId (Integer id){
			// TODO Auto-generated method stub
			return commentOnTopicRepository.findById(id).get();
		}

	}
