package com.sunkuet02.todoapplication.models;

import java.sql.Time;
import java.util.Date;

/**
 * Created by sun on 3/2/17.
 */
public class Task {
    int id;

    String username;

    String heading;

    String description;

    long time;

    public Task() {
    }

    public Task(String username, String heading, String description) {
        this.username = username;
        this.heading = heading;
        this.description = description;
        this.time = new Date().getTime();
    }

    public Task(String username, String heading, String description, long time) {
        this.username = username;
        this.heading = heading;
        this.description = description;
        this.time = time;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
