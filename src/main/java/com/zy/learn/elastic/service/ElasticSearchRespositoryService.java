package com.zy.learn.elastic.service;

import com.zy.learn.elastic.dto.Person;
import com.zy.learn.elastic.repository.PersonRespository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @AUTHOR zhangy
 * 2020-10-25  13:57
 */
@Service
@SuppressWarnings("all")
public class ElasticSearchRespositoryService {

    @Autowired
    private final PersonRespository personRespository;

    public ElasticSearchRespositoryService(PersonRespository personRespository) {
        this.personRespository = personRespository;
    }

    public List<Person>  getPersonListByRespository(String name, int age){
        return  personRespository.findPeopleByNameAndAge(name,age);
    }

    public List<Person>  getPersonListRespository(String descirbe){
        return  personRespository.findAllByDescribe(descirbe);
    }

    /**
     * 分页排序：注意排序字段不能为Text类型
     * @param pageable
     * @return
     */
    public Page<Person> getPersonByPage(Pageable pageable) {
//        QueryBuilder builder = QueryBuilders.matchAllQuery();
        QueryBuilder builder = QueryBuilders.matchQuery("name","小张");
        return personRespository.search(builder,  pageable);
    }

}
