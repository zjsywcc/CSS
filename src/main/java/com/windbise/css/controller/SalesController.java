package com.windbise.css.controller;

import com.alibaba.fastjson.JSON;
import com.windbise.css.entity.Good;
import com.windbise.css.service.GoodService;
import com.windbise.css.util.ReturnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Controller
@RequestMapping("/")
public class SalesController {

    Logger logger = LoggerFactory.getLogger(SalesController.class);

    @Autowired
    private GoodService goodService;

    @RequestMapping(value = "/getItemList", method = RequestMethod.POST)
    @ResponseBody
    public String getItemList(@RequestParam(value = "index") int index,
                              @RequestParam(value = "pageSize") int pageSize) {
        List<Good> goods = goodService.getGoodListByPage(index, pageSize);
        return ReturnData.result(0, "获取商品列表成功", JSON.toJSONString(goods));
    }

    @RequestMapping("/item")
    public String detail(HttpSession session, Model model) {
        if(session.getAttribute("userModel") != null) {
            Object userModel = session.getAttribute("userModel");
            model.addAttribute("userModel", userModel);
        }
        return "sales/item";
    }
}
