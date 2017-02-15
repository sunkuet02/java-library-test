package com.sunkuet02.search;

/**
 * Created by sun on 2/14/17.
 */
public class Item {
    private String name;
    private String description;

    public Item(String name) {
        this.name = name;
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
