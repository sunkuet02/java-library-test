package com.sunkuet02.dataes.controller;

import com.sunkuet02.dataes.model.User;
import com.sunkuet02.dataes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 6/7/17.
 */

@RestController
public class SearchController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @RequestMapping(value = "/searchByName", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUserByName(@RequestParam("name") String name) {
        return userService.searchByName(name);
    }

}
