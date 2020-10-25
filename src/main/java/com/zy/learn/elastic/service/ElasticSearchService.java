package com.zy.learn.elastic.service;

import com.zy.learn.elastic.dto.Person;
import com.zy.learn.elastic.repository.PersonRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @AUTHOR zhangy
 * 2020-10-25  13:57
 */
@Service
public class ElasticSearchService {

    @Autowired
    private final PersonRespository personRespository;


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public ElasticSearchService(PersonRespository personRespository) {
        this.personRespository = personRespository;
    }



    public List<Person>  getPerList(String name, int age){
        return  personRespository.findPeopleByNameAndAge(name,age);
    }


    public Object testPutElasticData(Person person) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId().toString())
                .withObject(person)
                .build();
        String documentId = elasticsearchTemplate.index(indexQuery);

        return documentId;
    }

    public Person getPerson(String id) {
        GetQuery query = new GetQuery();
        query.setId(id);
        Person person = elasticsearchTemplate.queryForObject(query, Person.class);
        return person;
    }
}
