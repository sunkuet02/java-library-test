package com.sunkuet02.todoapplication.controller;

import com.sunkuet02.todoapplication.models.Task;
import com.sunkuet02.todoapplication.service.TaskService;
import com.sunkuet02.todoapplication.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by sun on 3/2/17.
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService ;

    final static Logger logger = Logger.getLogger(ToDoAppController.class);

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTaskGet(HttpSession session, ModelMap modelMap) {
        logger.info("in task add get function **** ");
        return "task/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String showTasks(@ModelAttribute("task")Task task, ModelMap modelMap) {
        //task.setUsername(session.getAttribute("username").toString());
        logger.info("testing ******************8" + task.getDescription() + " " + task.getHeading());
        if(task.getDate()==null) {
            task.setDate(new Date());
        }
        taskService.addTask(task);

        return "redirect:/task/add";
    }
}
