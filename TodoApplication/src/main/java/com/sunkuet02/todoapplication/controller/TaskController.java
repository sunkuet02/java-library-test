package com.sunkuet02.todoapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunkuet02.todoapplication.models.Task;
import com.sunkuet02.todoapplication.service.TaskService;
import com.sunkuet02.todoapplication.utils.ElasticConnection;
import com.sunkuet02.todoapplication.validators.TaskValidator;
import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by sun on 3/2/17.
 */
@Controller
public class TaskController {
    private TaskService taskService;

    @InitBinder("task")
    protected void initTaskBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new TaskValidator());
    }

    final static Logger logger = Logger.getLogger(ToDoAppController.class);

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public String showAllTasks(HttpSession session, ModelMap modelMap) {
        logger.info("In showAllTask Function : **** ");
        //List<Task> tasks = taskService.getAllTask((String) session.getAttribute("username"));
        //modelMap.addAttribute("tasks", tasks);
        return "task/tasks";
    }

    @RequestMapping(value = "/task/add", method = RequestMethod.GET)
    public String addTaskGet(HttpSession session, ModelMap modelMap) {
        logger.info("in task add get function **** ");
        String username = (String) session.getAttribute("username");
        logger.info(username);
        if (username == null || username.equals("")) {
            return "task/tasks";
        }

        return "task/add";
    }

    @RequestMapping(value = "/task/add", method = RequestMethod.POST)
    public String addTask(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult,
                          HttpSession session, ModelMap modelMap) throws IOException {
        task.setUsername((String) session.getAttribute("username"));
        if (task.getTime() == 0) {
            task.setTime(new Date().getTime());
        }

        ElasticConnection elasticConnection = new ElasticConnection("elasticsearch", "localhost", 9300);
        elasticConnection.putMapping("todoapp", "task");
        IndexResponse response = elasticConnection.addTask(task);
        elasticConnection.closeConnection();

        return "redirect:/task";
    }

    @RequestMapping(value = "task/search", method = RequestMethod.GET)
    public @ResponseBody List<Task> searchTask(@RequestParam(value = "text", required = false) String text,
                          HttpSession session, ModelMap modelMap) throws IOException {
        ElasticConnection elasticConnection = new ElasticConnection("elasticsearch", "localhost", 9300);

        List<Task> searchResults = elasticConnection.searchTask((String) session.getAttribute("username"), text);

        return searchResults;
    }
}
