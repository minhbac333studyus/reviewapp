package com.rp;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test; 
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rp.data.ProductOnTopic;
import com.rp.data.TopicPage;
import com.rp.services.ProductOnTopicService;
import com.rp.services.TopicPageService;

@SpringBootTest
class ReviewProductApplicationTests {
	@Autowired
	private ProductOnTopicService productOnTopicService;
	@ Autowired 
	private TopicPageService topicPageService;
	@Test
	void contextLoads() {
	}
	@Test
	public void saveProductWithPage() {
		MockitoAnnotations.openMocks(this);
		ProductOnTopic product = new ProductOnTopic();
		product.setName("Test Name");
		List<ProductOnTopic> prods = new ArrayList<>();
		prods.add(product);
		TopicPage topic = new TopicPage();
		product.setTopicPage(topic);
		topic.setProductOnTopics(prods);
		productOnTopicService.create(product);
//		topicPageService.create(topic);
		
	}
}
