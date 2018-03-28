package com.windbise.css.controller.router;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


/**
 * Created by wangchengcheng on 2018/2/21.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String home(HttpSession session, Model model,
                       @RequestParam(value = "all", required = false, defaultValue = "true") boolean flag) {
        model.addAttribute("all", flag);
        if (session.getAttribute("userModel") != null) {
            Object userModel = session.getAttribute("userModel");
            model.addAttribute("userModel", userModel);
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(HttpSession session, Model model) {
        if (session.getAttribute("userModel") != null) {
            Object userModel = session.getAttribute("userModel");
            model.addAttribute("userModel", userModel);
        }
        return "login";
    }


}
