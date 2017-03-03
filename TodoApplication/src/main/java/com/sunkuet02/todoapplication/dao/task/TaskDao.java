package com.sunkuet02.todoapplication.dao.task;

import com.sunkuet02.todoapplication.models.Task;

import java.util.List;

/**
 * Created by sun on 3/2/17.
 */
public interface TaskDao {

    List<Task> getAllTask(String username);

    int addTask(Task task);
}
