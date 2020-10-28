package com.zy.learn.elastic.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @AUTHOR zhangy
 * 2020-10-25  14:09
 */
@Document(indexName = "person", type = "type3")
public class Person implements Serializable {

    @Id
    private String id ;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;

    private int age;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String provience;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String describe;


    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

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
