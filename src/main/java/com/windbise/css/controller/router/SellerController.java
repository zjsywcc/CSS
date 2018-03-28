package com.windbise.css.controller.router;

import com.windbise.css.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Controller
@RequestMapping("/seller")
public class SellerController {

    Logger logger = LoggerFactory.getLogger(SellerController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/publish")
    public String publish(HttpSession session, Model model) {
        if(session.getAttribute("userModel") != null) {
            Object userModel = session.getAttribute("userModel");
            model.addAttribute("userModel", userModel);
        }
        return "seller/publish";
    }

    @RequestMapping("/edit")
    public String edit(HttpSession session, Model model) {
        if(session.getAttribute("userModel") != null) {
            Object userModel = session.getAttribute("userModel");
            model.addAttribute("userModel", userModel);
        }
        return "seller/edit";
    }
}
