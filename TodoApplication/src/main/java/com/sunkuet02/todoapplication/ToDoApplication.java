package com.sunkuet02.todoapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by sun on 2/27/17.
 */
@SpringBootApplication
public class ToDoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);
    }

}