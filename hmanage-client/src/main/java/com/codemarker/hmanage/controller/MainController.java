package com.codemarker.hmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by sun on 5/3/17.
 */

@Controller
@EnableWebMvc
public class MainController {

    @RequestMapping("/")
    public String sayHello() {
        return "index";
    }

}
