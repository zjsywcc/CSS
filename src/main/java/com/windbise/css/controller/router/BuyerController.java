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
@RequestMapping("/buyer")
public class BuyerController {

    Logger logger = LoggerFactory.getLogger(BuyerController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/purchase")
    public String purchase(HttpSession session, Model model) {
        if(session != null && session.getAttribute("userModel") != null) {
            Object userModel = session.getAttribute("userModel");
            model.addAttribute("userModel", userModel);
        }
        return "buyer/purchase";
    }

    @RequestMapping("/cart")
    public String cart(HttpSession session, Model model) {
        if(session != null && session.getAttribute("userModel") != null) {
            Object userModel = session.getAttribute("userModel");
            model.addAttribute("userModel", userModel);
        }
        return "buyer/cart";
    }
}
