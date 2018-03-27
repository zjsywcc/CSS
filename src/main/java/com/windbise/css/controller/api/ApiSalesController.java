package com.windbise.css.controller.api;

import com.alibaba.fastjson.JSON;
import com.windbise.css.entity.Good;
import com.windbise.css.service.GoodService;
import com.windbise.css.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/20.
 */
@Controller
@RequestMapping("/api")
public class ApiSalesController {

    @Autowired
    private GoodService goodService;

    @RequestMapping(value = "/v1/goods", method = RequestMethod.POST)
    @ResponseBody
    public String goods(@RequestParam(value = "index") int index,
                        @RequestParam(value = "pageSize") int pageSize) {
        List<Good> goods = goodService.getGoodsByPage(index, pageSize);
        return ReturnData.result(0, "获取商品列表成功", JSON.toJSONString(goods));
    }

    @RequestMapping(value = "/v1/good/details", method = RequestMethod.POST)
    @ResponseBody
    public String details(@RequestParam(value = "goodId") int goodId) {
        Good good = goodService.getGoodById(goodId);
        return ReturnData.result(0, "获取商品详细信息成功", JSON.toJSONString(good));
    }

}
