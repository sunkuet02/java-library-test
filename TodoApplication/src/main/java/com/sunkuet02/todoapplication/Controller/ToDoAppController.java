package com.sunkuet02.todoapplication.controller;

import com.sunkuet02.todoapplication.models.User;
import com.sunkuet02.todoapplication.service.UserService;
import com.sunkuet02.todoapplication.utils.HashUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "redirect:/task";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(HttpSession session, ModelMap modelMap) {
        if(session.getAttribute("username") != null) {
            return "redirect:/task";
        }
        return "user/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute("user") User user, ModelMap modelMap) {

        user.setPassword(HashUtils.getMD5Hash(user.getPassword()));
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signinGet(HttpSession session, ModelMap modelMap) {
        if(session.getAttribute("username") != null) {
            return "redirect:/task";
        }
        return "user/signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(@ModelAttribute("user") User user, HttpSession session, ModelMap modelMap) {

        User tempUser = userService.findUser(user.getUsername());

        if(HashUtils.getMD5Hash(user.getPassword()).equals(tempUser.getPassword())) {
            session.setAttribute("username", user.getUsername());
            logger.info("successfully logged in ***** ");
        } else {
            logger.info("fail *************************");
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String signout(HttpSession session, ModelMap modelMap) {
        session.invalidate();
        return "redirect:/";
    }
}