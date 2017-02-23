package com.sunkuet02.spring.controller;

import com.sunkuet02.spring.dao.UserDao;
import com.sunkuet02.spring.dao.UserDaoImpl;
import com.sunkuet02.spring.models.User;
import com.sunkuet02.spring.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by sun on 2/19/17.
 */

@Controller
public class UserController {
    final static Logger logger = Logger.getLogger(UserController.class.toString());

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getName(ModelMap model) {
        logger.info("[[Log]] : In getName function ");

        return "redirect:/users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers(ModelMap model) {
        logger.info("[[Log]] : In showUsers function ****");

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "user/users";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") int id, ModelMap model) {
        logger.info("[[Log]] : In showUser function ** ");

        User user = userService.findUserById(id);
        if(user != null) {
            logger.info("[[LOG]] : " + user.toString());
        }
        model.addAttribute("user", user);
        return "user/users";
    }

    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable int id, ModelMap model) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String addUser(ModelMap model) {
        logger.info("In addUser get function ** ");

        model.addAttribute("user", new User());
        return "user/adduser";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") @Validated User user, ModelMap model) {
        logger.info("In addUser function ** ");

        userService.addUser(user);

        return "redirect:/users";
    }
}
