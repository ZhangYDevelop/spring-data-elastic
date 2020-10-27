package com.zy.learn.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @AUTHOR zhangy
 * 2020-10-25  13:53
 */
@SpringBootApplication
@EnableElasticsearchRepositories
public class MySpringDataelastic {

    public static void main(String[] args) {
        SpringApplication.run(MySpringDataelastic.class, args);
    }
}
