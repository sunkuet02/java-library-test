package com.sunkuet02.search;

/**
 * Created by sun on 2/16/17.
 */
public class ESModel {
    String index;
    String type;

    public ESModel() {
    }

    public ESModel(String index, String type) {
        this.index = index;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
