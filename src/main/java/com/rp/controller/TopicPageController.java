package com.rp.controller;

import com.rp.data.TopicPage;
import com.rp.services.*;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RequestMapping("/topicPages")
@Controller
@Log4j2
@Transactional
public class TopicPageController {
    @Autowired
    private TopicPageService topicPageService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TopicService topicService;
    @GetMapping("")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel<TopicPage>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        log.info("get all topic page");
        Pageable paging = PageRequest.of(page, size);
        Page<TopicPage> topicPages;
        try {
            topicPages = topicPageService.findAll(paging);
            if (topicPages.isEmpty()) {
                return new ResponseEntity<ResponseModel<TopicPage>>(HttpStatus.NO_CONTENT);
            }
            List<TopicPage> topicPageList = topicPages.getContent();
            ResponseModel<TopicPage> res = new ResponseModel<>();
            res.setDataForResponse(topicPages, topicPageList);
            return new ResponseEntity<ResponseModel<TopicPage>>(res, HttpStatus.OK);
        } catch (Exception e) {
            log.error("get All topicPages issue", () -> e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicPage> createTopicPage(@Valid @RequestBody TopicPage topicPage) {
        log.info("create topic page {}", () -> topicPage.toString());
        try {
            topicPageService.create(topicPage);
            return new ResponseEntity<TopicPage>(topicPage, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Create topicPages issue {}", () -> topicPage.toString());
            return new ResponseEntity<TopicPage>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/generate")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createSampleTopicPage() { 
    	Random rand = new Random();
    	List<String> topicContent = Arrays.asList("The Benefits of Meditation",
                "Tips for Healthy Eating on a Budget",
                "The Impact of Social Media on Society",
                "The Benefits of Traveling Solo" );
    	List<String> topicHeaderContent = Arrays.asList("Meditation is a practice that has been around for centuries and is used to promote relaxation, reduce stress, and improve overall health and well-being. Meditation can be done in various ways, including guided meditation, mindfulness meditation, and loving-kindness meditation. One of the main benefits of meditation is that it can help to reduce stress and anxiety, which can have a positive impact on both physical and mental health. Additionally, meditation has been shown to improve focus and concentration, boost the immune system, lower blood pressure, and reduce symptoms of depression. There are also different types of meditation techniques that can be used to target specific health concerns, such as chronic pain, insomnia, and addiction. Overall, the benefits of meditation are numerous, and incorporating this practice into your daily routine can have a significant impact on your overall health and well-being.",
    			"Social media has become an integral part of modern society, and its impact can be seen in various aspects of our lives. While social media platforms can be used for communication, entertainment, and information-sharing, they can also have negative effects on mental health, privacy, and social interaction. One of the main impacts of social media is the potential for addiction, as users can spend hours scrolling through their feeds and become disconnected from the world around them. Additionally, social media can contribute to feelings of loneliness and isolation, as people may feel left out or excluded from social events and conversations that are happening online. Social media can also be used as a tool for cyberbullying, harassment, and the spread of misinformation, which can have serious consequences for individuals and society as a whole. While social media has its benefits, it's important to recognize its potential risks and use it in a responsible and mindful way.",
    			"Renewable energy sources, such as solar, wind, and hydropower, are becoming increasingly popular as a way to reduce reliance on fossil fuels and decrease carbon emissions. While renewable energy sources have many benefits, they also have some disadvantages that need to be considered. One of the main advantages of renewable energy sources is that they are sustainable and can be replenished naturally, which means they have a smaller carbon footprint than traditional energy sources. Additionally, renewable energy sources can be used in remote areas and can provide energy security for communities that are not connected to the power grid. However, renewable energy sources can also have higher upfront costs and may not be as reliable as traditional energy sources, as they depend on factors such as weather conditions and geographical location. Additionally, renewable energy sources can have negative impacts on wildlife and ecosystems, particularly if they are not properly planned and implemented. Overall, the advantages and disadvantages of renewable energy sources need to be carefully considered when making decisions about energy policy and investments.",
    			"Self-care refers to any activity or practice that promotes physical, emotional, and mental well-being. While self-care is often associated with activities such as exercise, healthy eating, and getting enough sleep, it also includes practices such as mindfulness, meditation, and self-reflection. One of the main reasons why self-care is important for mental health is that it can help to reduce stress, anxiety, and depression. Additionally, self-care can improve self-esteem, boost energy levels, and promote a sense of well-being and fulfillment. Self-care can also help individuals to develop a stronger sense of self-awareness and self-compassion, which can have a positive impact on relationships and overall life satisfaction"
    			);
    	List<String> topicMiddleContent = Arrays.asList("Traveling solo can be a life-changing experience that can help you to grow and develop as a person. One of the main benefits of solo travel is that it allows you to push yourself out of your comfort zone and experience new things without the influence of others. Solo travel can also be a great way to improve your confidence and independence, as you are solely responsible for your itinerary and decision-making. Additionally, solo travel can lead to more authentic and meaningful experiences, as you are forced to engage with locals and immerse yourself in the local culture. However, solo travel can also be challenging, particularly for those who are not used to being alone for extended periods of time. It's important to take necessary precautions and plan ahead when traveling solo to ensure a safe and enjoyable experience.",
    				"Traveling solo can be a life-changing experience that can help you to grow and develop as a person. One of the main benefits of solo travel is that it allows you to push yourself out of your comfort zone and experience new things without the influence of others. Solo travel can also be a great way to improve your confidence and independence, as you are solely responsible for your itinerary and decision-making. Additionally, solo travel can lead to more authentic and meaningful experiences, as you are forced to engage with locals and immerse yourself in the local culture. However, solo travel can also be challenging, particularly for those who are not used to being alone for extended periods of time. It's important to take necessary precautions and plan ahead when traveling solo to ensure a safe and enjoyable experience.",
    				"Artificial intelligence (AI) is a rapidly growing field that has the potential to revolutionize many aspects of our lives, from healthcare to transportation to entertainment. One of the main benefits of AI is that it can automate many repetitive and mundane tasks, allowing humans to focus on more complex and creative work. Additionally, AI can help to improve efficiency and productivity in various industries, as it can analyze large amounts of data and make predictions and decisions based on that data. However, AI also raises concerns about job displacement and the ethical implications of relying on machines to make important decisions. It's important for researchers, policymakers, and the general public to engage in discussions about the future of AI and to consider the potential risks and benefits of this rapidly evolving technology.",
    				"Music has the power to affect our mood, energy levels, and overall sense of well-being. Studies have shown that listening to music can have a positive impact on productivity, creativity, and motivation, particularly when it comes to tasks that require focus and concentration. Additionally, music can be used as a form of therapy to help individuals manage stress, anxiety, and depression. However, the impact of music on mood and productivity can vary depending on the type of music, the individual's personal preferences, and the context in which the music is being listened to. It's important to choose music that is appropriate for the task at hand and to be mindful of how music is affecting your mood and productivity."
    			);
    	for(int i = 0 ; i < 10 ;i++) {
    		TopicPage t =  new TopicPage();
    		t.setTopic(topicService.findbyId(rand.nextInt(4)+1));
    		t.setAuthor(authorService.findbyId(rand.nextInt(4)+1));
    		t.setTopicContent(topicContent.get(rand.nextInt(4)));
    		t.setTopicHeaderContent(topicHeaderContent.get(rand.nextInt(4)));
    		t.setTopicMiddleContent(topicMiddleContent.get(rand.nextInt(4))); 
    		
            topicPageService.create(t);
    	}
            return new ResponseEntity<HttpStatus>( HttpStatus.CREATED);
   
    }

    @PutMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicPage> updateTopicPage(@PathVariable Integer id, @Valid @RequestBody TopicPage topicPage) {
        log.info("update topic page {}", () -> topicPage.toString());
        try {
            topicPageService.updateById(id, topicPage);
            return new ResponseEntity<TopicPage>(topicPage, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Create topicPages issue {}", () -> topicPage.toString());
            return new ResponseEntity<TopicPage>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicPage> getTopicPageById(@PathVariable(value = "id") Integer id) {
        TopicPage topicPage = topicPageService.findbyId(id);
        log.info("get topic page {}", () -> topicPage.toString());
        try {
            return new ResponseEntity<TopicPage>(topicPage, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get topicPages issue {}", () -> topicPage.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/authors/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel<TopicPage>> getAllTopicPageByAuthorId(@PathVariable(value = "id") Integer id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<TopicPage> topicPages;
        topicPages = topicPageService.findAlltopicPagesByAuthorId(id, paging);
        try {

            if (topicPages.isEmpty()) {
                return new ResponseEntity<ResponseModel<TopicPage>>(HttpStatus.NO_CONTENT);
            }
            log.info("get topic page {}", () -> topicPages.toString());
            List<TopicPage> topicPageList = topicPages.getContent();
            ResponseModel<TopicPage> res = new ResponseModel<>();
            res.setDataForResponse(topicPages, topicPageList);
            return new ResponseEntity<ResponseModel<TopicPage>>(res, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get topicPages issue {}", () -> topicPages.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
