package com.windbise.css.controller.router;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;


/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Controller
@RequestMapping("/")
public class SalesController {

    Logger logger = LoggerFactory.getLogger(SalesController.class);


    @RequestMapping("/item")
    public String item(HttpSession session, Model model) {
        if(session.getAttribute("userModel") != null) {
            Object userModel = session.getAttribute("userModel");
            model.addAttribute("userModel", userModel);
        }
        return "sales/item";
    }
}
