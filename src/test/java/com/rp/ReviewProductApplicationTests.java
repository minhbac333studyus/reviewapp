//package com.rp;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.transaction.Transactional;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import com.rp.controller.AuthorController;
//import com.rp.data.Category;
//import com.rp.data.ProductOnTopic;
//import com.rp.data.Topic;
//import com.rp.data.TopicPage;
//import com.rp.repositories.CategoryRepository;
//import com.rp.repositories.TopicPageRepository;
//import com.rp.repositories.TopicRepository;
//import com.rp.services.CategoryService;
//import com.rp.services.ProductOnTopicService;
//import com.rp.services.TopicPageService;
//import com.rp.services.TopicService;
//
//import lombok.extern.log4j.Log4j2;
//@Log4j2
//@SpringBootTest
//@Transactional
//class ReviewProductApplicationTests {
//	@Autowired
//	private ProductOnTopicService productOnTopicService;
//	@ Autowired
//	private TopicPageService topicPageService;
//	@Autowired TopicService topicService;
//	@Autowired
//	CategoryRepository categoryRepository;
//	@Autowired
//	TopicRepository topicRepository;
//
//	@Test
//	void contextLoads() {
//	}
////	@Test
////	public void saveProductWithPage() {
////		MockitoAnnotations.openMocks(this);
////		ProductOnTopic product = new ProductOnTopic();
////		product.setName("Test Name");
////		List<ProductOnTopic> prods = new ArrayList<>();
////		prods.add(product);
////		TopicPage topic = new TopicPage();
////		product.setTopicPage(topic);
////		topic.setProductOnTopics(prods);
////		productOnTopicService.create(product);
//////		topicPageService.create(topic);
////	}
////
////	@Test
////	@Transactional
////	public void createTopicWithCategory() {
////		Category c1 = new Category();
////		c1.setName("Technology");
////		Topic t1 = new Topic();
////		t1.setName("Gameing Gear");
////		t1.setCategory(c1);
////		topicRepository.save(t1);
////	}
////	@Test
////	@Transactional
////	   @Rollback(false)
////	public void CreateTopicPagesWithTopic() {
////
////		Topic t1 = topicRepository.findById(1).get();
////		TopicPage p1 = new TopicPage();
////		p1.setTopicTitle("Best 5 gaming phone for teacher");
////		TopicPage p2 = new TopicPage();
////		p2.setTopicTitle("Best 5 android phone for school");
////		Set<TopicPage> topicPages = t1.getTopicPages();
////		topicPages.add(p1);
////		topicPages.add(p2);
////		p1.getTopics().add(t1);
////		p2.getTopics().add(t1);
////		t1.setTopicPages(topicPages);
////		topicService.create(t1);
////
////	}
////
////	@Test
////	@Transactional
////	public void findTopicByTopicTitle() {
////		List<TopicPage> t1 = topicPageService.findAllTopicPageByTopicTilte("Best 5 gaming phone for student");
////		log.debug(t1.get(0).getTopics().toString());
////
////
////	}
//	@Test
//	@Transactional
//	   @Rollback(false)
//	public void createTopicWithPage() {
////		Category c1 = new Category();
////		c1.setName("Technology");
////		Topic t1 = new Topic();
////		t1.setName("Gameing Gear");
////		t1.setCategory(c1);
////		topicRepository.save(t1);
//
////		Category c1 = categoryRepository.findById(1).get();
////		System.out.println(c1.getTopics().get(0).getCategory().getName().toString());
//		Topic t1 = topicRepository.findById(1).get();
////		log.debug(t1.getTopicPages().toString());
//		TopicPage tp1 = topicPageService.findbyId(10);
//		t1.removeTopicPage(tp1);
//		topicService.updateById(1,t1);
//		topicPageService.create(tp1);
////		log.debug(t1.getTopicPages().toString());
//
//
//	}
//}
