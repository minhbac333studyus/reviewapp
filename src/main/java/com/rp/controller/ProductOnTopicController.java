package com.rp.controller;

import com.rp.data.ProductOnTopic;
import com.rp.services.ProductOnTopicService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("/productOnTopics")
@Controller
@Log4j2
@Transactional
public class ProductOnTopicController {

    @Autowired
    private ProductOnTopicService productOnTopicService;

    @GetMapping("/")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductOnTopic>> getAll() {
        log.info("get all product on topic");
        try {
            List<ProductOnTopic> productOnTopics = productOnTopicService.findAll();
            if (productOnTopics.isEmpty()) {
                return new ResponseEntity<List<ProductOnTopic>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<ProductOnTopic>>(productOnTopics, HttpStatus.OK);
        } catch (Exception e) {
            log.error("get All productOnTopics issue", () -> e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductOnTopic> createProductOnTopic(@Valid @RequestBody ProductOnTopic productOnTopic) {
        log.info("create product on topic {}", () -> productOnTopic.toString());
        try {
            productOnTopicService.create(productOnTopic);
            return new ResponseEntity<ProductOnTopic>(productOnTopic, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Create productOnTopics issue {}", () -> productOnTopic.toString());
            return new ResponseEntity<ProductOnTopic>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductOnTopic> updateProductOnTopic(@PathVariable Integer id, @Valid @RequestBody ProductOnTopic productOnTopic) {
        log.info("update product on topic {}", () -> productOnTopic.toString());
        try {
            productOnTopicService.updateById(id, productOnTopic);
            return new ResponseEntity<ProductOnTopic>(productOnTopic, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Create productOnTopics issue {}", () -> productOnTopic.toString());
            return new ResponseEntity<ProductOnTopic>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductOnTopic> getProductOnTopicById(@PathVariable(value = "id") Integer id) {
        ProductOnTopic productOnTopic = productOnTopicService.findbyId(id);
        log.info("get product on topic {}", () -> productOnTopic.toString());
        try {
            return new ResponseEntity<ProductOnTopic>(productOnTopic, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}