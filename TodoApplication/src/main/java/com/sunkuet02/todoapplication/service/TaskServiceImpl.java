package com.sunkuet02.todoapplication.service;

import com.sunkuet02.todoapplication.dao.task.TaskDao;
import com.sunkuet02.todoapplication.dao.user.UserDao;
import com.sunkuet02.todoapplication.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sun on 3/2/17.
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

    TaskDao taskDao;

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public List<Task> getAllTask(String username) {
        return taskDao.getAllTask(username);
    }

    @Override
    public void addTask(Task task) {
        taskDao.addTask(task);
    }
}
