package com.zy.learn.elastic.controller;

import com.zy.learn.elastic.dto.Person;
import com.zy.learn.elastic.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @AUTHOR zhangy
 * 2020-10-25  13:57
 */
@RestController
@RequestMapping("/api/elastic")
public class ElasticSearchController {

    @Autowired
    private ElasticSearchService elasticSearchService;


    @PutMapping("/putPerson")
    private ResponseEntity testPutElasticData (@RequestBody Person person) {
        return  ResponseEntity.ok(elasticSearchService.testPutElasticData(person));
    }

    @GetMapping("/getPerson")
    private ResponseEntity<Person> getPerson ( String id) {
        return  ResponseEntity.ok(elasticSearchService.getPerson(id));
    }

    @GetMapping("/getPersonList")
    private ResponseEntity<List<Person>> getPersonList () {
        return  ResponseEntity.ok(elasticSearchService.getPerList("zhangyu", 12));
    }
}
