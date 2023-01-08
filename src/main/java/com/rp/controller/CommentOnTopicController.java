package com.rp.controller;

import com.rp.data.CommentOnTopicPage;
import com.rp.services.CommentOnTopicService;
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

@RequestMapping("/commentOnTopics")
@Controller
@Log4j2
@Transactional
public class CommentOnTopicController {
    @Autowired
    private CommentOnTopicService commentOnTopicService;

    @GetMapping("/")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel<CommentOnTopicPage>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<CommentOnTopicPage> CommentOnTopicPagePage;
        try {
            CommentOnTopicPagePage = commentOnTopicService.findAll(paging);
            if (CommentOnTopicPagePage.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<CommentOnTopicPage> CommentOnTopicPages = CommentOnTopicPagePage.getContent();
            ResponseModel<CommentOnTopicPage> res = new ResponseModel<>();
            res.setDataForResponse(CommentOnTopicPagePage, CommentOnTopicPages);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            log.error("get All CommentOnTopicPages issue", () -> e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentOnTopicPage> createCommentOnTopic(@Valid @RequestBody CommentOnTopicPage commentOnTopic) {
        log.info("create comment on topic {}", () -> commentOnTopic.toString());
        try {
            commentOnTopicService.create(commentOnTopic);
            return new ResponseEntity<CommentOnTopicPage>(commentOnTopic, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Create commentOnTopics issue {}", () -> commentOnTopic.toString());
            return new ResponseEntity<CommentOnTopicPage>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentOnTopicPage> updateCommentOnTopic(@PathVariable Integer id,
                                                                   @Valid @RequestBody CommentOnTopicPage commentOnTopic) {
        log.info("update comment on topic {}", () -> commentOnTopic.toString());
        try {
            commentOnTopicService.updateById(id, commentOnTopic);
            return new ResponseEntity<CommentOnTopicPage>(commentOnTopic, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Create commentOnTopics issue {}", () -> commentOnTopic.toString());
            return new ResponseEntity<CommentOnTopicPage>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentOnTopicPage> getCommentOnTopicById(@PathVariable(value = "id") Integer id) {
        CommentOnTopicPage commentOnTopic = commentOnTopicService.findbyId(id);
        log.info("get comment on topic {}", () -> commentOnTopic.toString());
        try {
            return new ResponseEntity<CommentOnTopicPage>(commentOnTopic, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get commentOnTopics issue {}", () -> commentOnTopic.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
