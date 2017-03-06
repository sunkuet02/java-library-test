package com.sunkuet02.todoapplication.service;

import com.sunkuet02.todoapplication.models.Task;

import java.util.List;

/**
 * Created by sun on 3/2/17.
 */
public interface TaskService {

    List<Task> getAllTask(String username);

    void addTask(Task task);

    List<Task> search(String username, String text);
}
