package com.sunkuet02.dataes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;

/**
 * Created by sun on 6/7/17.
 */

@Document(indexName = "tigerit", type = "user")
public class User implements Serializable {

    @Id
    String id;

    @JsonProperty("boyosh")
    int age;

    @JsonProperty("name")
    @MultiField(
            mainField = @Field(type = FieldType.String),
            otherFields = {
                    @InnerField(index = FieldIndex.not_analyzed, suffix = "raw", type = FieldType.String)
            }
    )
    private String name;

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
