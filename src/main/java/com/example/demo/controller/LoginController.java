package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(boolean error, Model model){

        model.addAttribute("error", error);
//        //获取当前的Subject
//        Subject currentUser = SecurityUtils.getSubject();
//        if (currentUser.isAuthenticated() || currentUser.isRemembered()) {
//            return "redirect:/home";
//        } else {
            return "login";
//        }
    }
}
