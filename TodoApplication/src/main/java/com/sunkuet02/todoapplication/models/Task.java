package com.sunkuet02.todoapplication.models;

import java.util.Date;

/**
 * Created by sun on 3/2/17.
 */
public class Task {
    int id;

    String username;

    String heading;

    String description;

    Date date;

    public Task() {
    }

    public Task(String username, String heading, String description) {
        this.username = username;
        this.heading = heading;
        this.description = description;
        this.date = new Date();
    }

    public Task(String username, String heading, String description, Date date) {
        this.username = username;
        this.heading = heading;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
