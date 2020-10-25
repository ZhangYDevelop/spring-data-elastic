package com.zy.learn.elastic.repository;

import com.zy.learn.elastic.dto.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @AUTHOR zhangy
 * 2020-10-25  19:46
 */
@Repository
public interface PersonRespository extends CrudRepository<Person, String> {

    List<Person> findPeopleByNameAndAge(String name, int ange);

}
