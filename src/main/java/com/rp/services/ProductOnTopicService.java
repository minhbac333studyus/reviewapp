package com.rp.services;

import com.rp.data.ProductOnTopic;
import com.rp.repositories.ProductOnTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ProductOnTopicService {
    @Autowired
    ProductOnTopicRepository productOnTopicRepository;

    public List<ProductOnTopic> findAll() {
        // TODO Auto-generated method stub
        return productOnTopicRepository.findAll();
    }

    public ProductOnTopic updateById(Integer id, @Valid ProductOnTopic productOnTopic) {
        // TODO Auto-generated method stub
        ProductOnTopic productOnTopicDb = productOnTopicRepository.findById(id).get();
        productOnTopicDb = productOnTopic;
        productOnTopicDb.setId(id);
        return productOnTopicRepository.save(productOnTopicDb);
    }

    public ProductOnTopic create(@Valid ProductOnTopic productOnTopic) {
        // TODO Auto-generated method stub
        productOnTopicRepository.save(productOnTopic);
        return productOnTopic;
    }

    public ProductOnTopic findbyId(Integer id) {
		// TODO Auto-generated method stub
		return productOnTopicRepository.findById(id).get();
	}

}
