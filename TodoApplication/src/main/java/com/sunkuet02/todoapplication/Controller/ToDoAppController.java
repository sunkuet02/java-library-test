package com.sunkuet02.todoapplication.Controller;

import com.sunkuet02.todoapplication.Models.User;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sun on 2/28/17.
 */
@Controller
public class ToDoAppController {

    final static Logger logger = Logger.getLogger(ToDoAppController.class);


    @RequestMapping("/")
    public String welcome(ModelMap modelMap) {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, headers="Accept=application/json")
    public String signup(@RequestBody User user, ModelMap modelMap) {
        logger.info(user.getUsername() + " " + user.getPassword() + " " + user.getEmail());
        return "index";
    }


}