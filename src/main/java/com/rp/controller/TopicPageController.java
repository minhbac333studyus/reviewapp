package com.rp.controller;

import com.rp.data.TopicPage;
import com.rp.services.TopicPageService;
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
import java.util.List;

@RequestMapping("/topicPages")
@Controller
@Log4j2
@Transactional
public class TopicPageController {
    @Autowired
    private TopicPageService topicPageService;

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
