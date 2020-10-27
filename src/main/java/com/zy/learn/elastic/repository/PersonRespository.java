package com.zy.learn.elastic.repository;

import com.zy.learn.elastic.dto.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @AUTHOR zhangy
 * 2020-10-25  19:46
 */
@Repository
public interface PersonRespository extends ElasticsearchRepository<Person, String> {

    List<Person> findPeopleByNameAndAge(String name, int ange);


    List<Person> findAllByDescribe(String descirbe);

    Page<Person> findAllByName(String name, Pageable page);

}
