package com.windbise.css.controller.api;

import com.alibaba.fastjson.JSON;
import com.windbise.css.entity.Good;
import com.windbise.css.entity.User;
import com.windbise.css.service.GoodService;
import com.windbise.css.service.UserService;
import com.windbise.css.util.ReturnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;


/**
 * Created by wangchengcheng on 2018/3/20.
 */
@Controller
@RequestMapping("/api")
public class ApiSellerController {

    Logger logger = LoggerFactory.getLogger(ApiSellerController.class);

    @Autowired
    private GoodService goodService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/seller/publish", method= RequestMethod.POST)
    @ResponseBody
    public String publish(@RequestParam(value = "title") String title,
                          @RequestParam(value = "intro") String intro,
                          @RequestParam(value = "photo") String photoURI,
                          @RequestParam(value = "content") String content,
                          @RequestParam(value = "price") int price,
                          HttpSession session) {
        User currentUser = userService.getCurrentUser(session);
        if(currentUser == null) {
            return "redirect:/login";
        }
        int sellerId = currentUser.getId();

        Good good = new Good();
        good.setSellerId(sellerId);
        good.setContent(content);
        good.setBuyerId(-1);
        good.setCost(price);
        good.setCreateTime(System.currentTimeMillis() / 1000);
        good.setDeleted(false);
        good.setIntro(intro);
        good.setPhoto(photoURI);
        good.setSoldNum(0);
        good.setTitle(title);

        int goodId = goodService.addGood(good);

        return ReturnData.result(0, "发布商品成功", JSON.toJSONString(null));
    }
}
