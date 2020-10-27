package com.zy.learn.elastic.service;

import com.zy.learn.elastic.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;


/**
 * 通过 elasticsearchRestTemplate 操作elasticsearch
 */
@Service
public class ElasticSearchTemplateService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 创建索同时记录
     * @param person
     * @return
     */
    public Object putPersonByRestTemplate(Person person) {
        IndexCoordinates indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(person.getClass());
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId().toString())
                .withObject(person)
                .build();
        String documentId = elasticsearchRestTemplate.index(indexQuery, indexCoordinates);

        return documentId;
    }

    /**
     * 根据ID获取记录
     * @param id
     * @return
     */
    public Person getPersonByRestTemplate(String id) {
        Person person = elasticsearchRestTemplate
                .queryForObject(GetQuery.getById(id.toString()), Person.class);
        return person;
    }

    /**
     * 删除索引
     * @return
     */
    public boolean deleteIndex() {
        return  elasticsearchRestTemplate.deleteIndex(Person.class);
    }
}
