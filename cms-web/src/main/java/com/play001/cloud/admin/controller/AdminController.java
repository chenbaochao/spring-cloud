package com.play001.cloud.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/admin")
public class AdminController {


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){

        return "webfront/main.html";
    }


}
