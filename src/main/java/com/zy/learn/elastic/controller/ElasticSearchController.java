package com.zy.learn.elastic.controller;

import com.zy.learn.elastic.dto.Person;
import com.zy.learn.elastic.service.ElasticSearchCommonService;
import com.zy.learn.elastic.service.ElasticSearchRespositoryService;
import com.zy.learn.elastic.service.ElasticSearchTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "ElasticSearch 操作Api")
@RestController
@SuppressWarnings("all")
@RequestMapping("/api/elastic")
public class ElasticSearchController {

    @Autowired
    private ElasticSearchRespositoryService elasticSearchService;

    @Autowired
    private ElasticSearchTemplateService elasticSearchTemplateService;

    @Autowired
    private ElasticSearchCommonService elasticSearchCommonService;

    @ApiOperation(value = "创建索引")
    @GetMapping("/createIndex")
    private ResponseEntity<Boolean> createIndex () {
        return  ResponseEntity.ok(elasticSearchTemplateService.createIndex(Person.class));
    }

    @ApiOperation(value = "判断索引是否存在")
    @GetMapping("/existIndex")
    private ResponseEntity<Boolean> existIndex () {
        return  ResponseEntity.ok(elasticSearchTemplateService.existIndex(Person.class));
    }

    @ApiOperation(value = "删除索引")
    @GetMapping("/deleteIndex")
    private ResponseEntity<Boolean> deleteIndex () {
        return  ResponseEntity.ok(elasticSearchTemplateService.deleteIndex(Person.class));
    }

    @ApiOperation(value = "添加人员信息")
    @PutMapping("/putPerson")
    private ResponseEntity putPerson (@RequestBody Person person) {
        return  ResponseEntity.ok(elasticSearchTemplateService.putPersonByRestTemplate(person));
    }

    @ApiOperation(value = "更新人员信息")
    @PostMapping("/updatePerson")
    private ResponseEntity updatePerson (@RequestBody Person person) {
        return  ResponseEntity.ok(elasticSearchService.updatePerson(person));
    }

    @ApiOperation(value = "根据ID获取人员信息")
    @GetMapping("/getPersonById")
    private ResponseEntity<Person> getPersonByRestTemplate ( String id) {
        return  ResponseEntity.ok(elasticSearchTemplateService.getPersonByRestTemplate(id));
    }

    @ApiOperation(value = "根据Name age 获取所有人员信息")
    @GetMapping("/getPersonListByNamAndAge")
    private ResponseEntity<List<Person>> getPersonListByRespository (String name, int age) {
        return  ResponseEntity.ok(elasticSearchService.getPersonListByRespository(name, age));
    }

    @ApiOperation(value = "根据人员描述 获取所有人员信息")
    @GetMapping("/getPersonListByPersonDescirbe")
    private ResponseEntity<List<Person>> getPersonListRespository (String describe) {
        return  ResponseEntity.ok(elasticSearchService.getPersonListRespository(describe));
    }

    @ApiOperation(value = "分页获取所有人员信息")
    @GetMapping("/getPersonByPage")
    private ResponseEntity<Page<Person>> getPersonByPage (int page, int size, String sort) {
        return  ResponseEntity.ok(elasticSearchService.getPersonByPage(page, size, sort));
    }

    @ApiOperation(value = "根据关键字获取所有人员信息并高亮显示")
    @GetMapping("/getPersonByPageAndKeyWorkHeighLight")
    private ResponseEntity<Page<Person>> getPersonByPageAndKeyWorkHeighLight (int page, int size, String keyWork) {
        return  ResponseEntity.ok(elasticSearchService.getPersonByPageAndKeyWorkHeighLight(page, size, keyWork));
    }
}
