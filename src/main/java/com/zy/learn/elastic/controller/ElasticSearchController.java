package com.zy.learn.elastic.controller;

import com.zy.learn.elastic.dto.Person;
import com.zy.learn.elastic.service.ElasticSearchRespositoryService;
import com.zy.learn.elastic.service.ElasticSearchTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @AUTHOR zhangy
 * 2020-10-25  13:57
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/api/elastic")
public class ElasticSearchController {

    @Autowired
    private ElasticSearchRespositoryService elasticSearchService;

    @Autowired
    private ElasticSearchTemplateService elasticSearchTemplateService;


    @PutMapping("/putPersonByRestTemplate")
    private ResponseEntity putPersonByRestTemplate (@RequestBody Person person) {
        return  ResponseEntity.ok(elasticSearchTemplateService.putPersonByRestTemplate(person));
    }

    @GetMapping("/getPersonByRestTemplate")
    private ResponseEntity<Person> getPersonByRestTemplate ( String id) {
        return  ResponseEntity.ok(elasticSearchTemplateService.getPersonByRestTemplate(id));
    }


    @GetMapping("/getPersonListByRespository")
    private ResponseEntity<List<Person>> getPersonListByRespository () {
        return  ResponseEntity.ok(elasticSearchService.getPersonListByRespository("张宇", 27));
    }

    @GetMapping("/getPersonListRespository")
    private ResponseEntity<List<Person>> getPersonListRespository () {
        return  ResponseEntity.ok(elasticSearchService.getPersonListRespository("杂交水稻"));
    }

    @GetMapping("/getPersonByPage")
    private ResponseEntity<Page<Person>> getPersonByPage (@PageableDefault(page=0,size=2,sort="id,asc")Pageable pageable) {
        return  ResponseEntity.ok(elasticSearchService.getPersonByPage(pageable));
    }

}
