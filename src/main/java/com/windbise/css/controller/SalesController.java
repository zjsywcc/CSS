package com.windbise.css.controller;

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

    @RequestMapping("/item")
    public String home(HttpSession session, Model model) {
        if(session.getAttribute("userModel") != null) {
            Object userModel = session.getAttribute("userModel");
            model.addAttribute("userModel", userModel);
        }
        return "sales/item";
    }
}
