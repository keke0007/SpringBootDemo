package com.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.annotations.Mapping;

/**
 * @ Author：ke
 * @ Date： 2019/6/18 14:13
 * @ Version 1.0
 */
@SpringBootApplication
@Mapping
public class AppElasticsearch {
    public static void main(String[] args) {
        SpringApplication.run(AppElasticsearch.class, args);
    }
}
