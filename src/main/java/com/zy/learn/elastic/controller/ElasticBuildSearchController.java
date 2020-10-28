package com.zy.learn.elastic.controller;

import com.zy.learn.elastic.dto.Person;
import com.zy.learn.elastic.service.ElasticSearchCommonService;
import com.zy.learn.elastic.service.ElasticSearchRespositoryService;
import com.zy.learn.elastic.service.ElasticSearchTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @AUTHOR zhangy
 * 2020-10-25  13:57
 */
@Api(tags = "ElasticSearch Builder 操作Api")
@RestController
@SuppressWarnings("all")
@RequestMapping("/api/elastic/build")
public class ElasticBuildSearchController {

    @Autowired
    private ElasticSearchCommonService elasticSearchCommonService;

    @ApiOperation(value = "获取所有人员信息 matchAllQuery")
    @GetMapping("/matchAllQuery")
    private ResponseEntity<List<Person>> matchAllQuery () {
        return  ResponseEntity.ok(elasticSearchCommonService.matchAllQuery());
    }

    @ApiOperation(value = "获取所有人员信息 matchQuery")
    @GetMapping("/matchQuery")
    private ResponseEntity<List<Person>> matchQuery (String fild, String text) {
        return  ResponseEntity.ok(elasticSearchCommonService.matchQuery(fild, text));
    }

    @ApiOperation(value = "获取所有人员信息 commonTermsQuery")
    @GetMapping("/commonTermsQuery")
    private ResponseEntity<List<Person>> commonTermsQuery (String fild, String text) {
        return  ResponseEntity.ok(elasticSearchCommonService.commonTermsQuery(fild, text));
    }
}
