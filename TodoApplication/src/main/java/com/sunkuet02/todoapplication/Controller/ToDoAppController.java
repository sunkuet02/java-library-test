package com.sunkuet02.todoapplication.controller;

import com.sunkuet02.todoapplication.dao.user.UserDao;
import com.sunkuet02.todoapplication.dao.user.UserDaoImpl;
import com.sunkuet02.todoapplication.models.User;
import com.sunkuet02.todoapplication.service.UserService;
import com.sunkuet02.todoapplication.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by sun on 2/28/17.
 */
@Controller
public class ToDoAppController {

    private UserService userService ;

    final static Logger logger = Logger.getLogger(ToDoAppController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(HttpSession session, ModelMap modelMap) {
        modelMap.addAttribute("username", session.getAttribute("username"));
        return "home";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(ModelMap modelMap) {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute("user") User user, ModelMap modelMap) {
        logger.info(user.getUsername() + " " + user.getPassword() + " " + user.getEmail());
        userService.addUser(user);
        return "redirect:/signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signinGet(ModelMap modelMap) {
        return "signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(HttpSession session, @ModelAttribute("user") User user, ModelMap modelMap) {

        logger.info(user.getUsername() + " " + user.getPassword() + " " + user.getEmail());

        User tempUser = userService.findUser(user.getUsername());

        logger.info("**********" + tempUser.getPassword());
        if(user.getPassword().equals(tempUser.getPassword())) {
            session.setAttribute("username", user.getUsername());
            logger.info("successfully logged in ***** ");
        } else {
            logger.info("fail *************************");
        }

        return "redirect:/";
    }
}