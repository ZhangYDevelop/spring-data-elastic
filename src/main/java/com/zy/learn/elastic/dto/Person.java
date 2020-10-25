package com.zy.learn.elastic.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @AUTHOR zhangy
 * 2020-10-25  14:09
 */
@Document(indexName = "person", type = "type3")
public class Person implements Serializable {

    @Id
    private String id ;


    private String name;

    private int age;

    private String provience;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProvience() {
        return provience;
    }

    public void setProvience(String provience) {
        this.provience = provience;
    }
}
