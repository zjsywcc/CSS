package com.windbise.css.controller.api;

import com.windbise.css.entity.Good;
import com.windbise.css.entity.Good.GoodBuilder;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import static com.windbise.css.util.Constants.IMAGE_DIR_PATH;
import static com.windbise.css.util.Constants.IMAGE_PATH_PREFIX;
import static com.windbise.css.util.Constants.RANDOM;


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

    @RequestMapping(value = "/v1/seller/upload", headers = ("content-type=multipart/form-data"), method= RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile != null && multipartFile.getSize() > 0) {
            String originFileName = multipartFile.getOriginalFilename();
            String fileName = System.currentTimeMillis() + RANDOM.nextInt(100)
                    + originFileName.substring(originFileName.lastIndexOf("."));
            try {
                File file = new File(IMAGE_DIR_PATH + fileName);
                file = file.getAbsoluteFile();
                multipartFile.transferTo(file);
                logger.info("Save file path: {}", file.getAbsolutePath());
            } catch (IOException e) {
                logger.info("Save file error: {}", e.toString());
                return ReturnData.result(-1, "保存图片失败!", null);
            }
            return ReturnData.result(0, "保存图片成功!", IMAGE_PATH_PREFIX + fileName);
        }
        return ReturnData.result(-2, "文件为空!", null);
    }

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
            return ReturnData.result(-2, "未登录!", null);
        }
        int sellerId = currentUser.getId();
        Good good = new GoodBuilder().setSellerId(sellerId)
                .setBuyerId(-1)
                .setTitle(title)
                .setIntro(intro)
                .setContent(content)
                .setCost(price)
                .setCreateTime(System.currentTimeMillis() / 1000)
                .setDeleted(false)
                .setPhoto(photoURI)
                .setSoldNum(0)
                .build();

        goodService.addGood(good);

        return ReturnData.result(0, "发布商品成功", null);
    }

    @RequestMapping(value = "/v1/seller/delete/good", method= RequestMethod.POST)
    @ResponseBody
    public String deleteGood(@RequestParam(value = "goodId") int goodId, HttpSession session) {
        User currentUser = userService.getCurrentUser(session);
        if(currentUser == null) {
            return ReturnData.result(-2, "未登录!", null);
        }
        int row = goodService.deleteGood(goodId);
        if(row == 1) {
            return ReturnData.result(0, "删除商品成功!", null);
        } else {
            return ReturnData.result(-1, "删除商品失败!", null);
        }
    }

    @RequestMapping(value = "/v1/seller/edit/good", method= RequestMethod.POST)
    @ResponseBody
    public String editGood(
            @RequestParam(value = "goodId") int goodId,
            @RequestParam(value = "goodTitle") String goodTitle,
            @RequestParam(value = "goodIntro") String goodIntro,
            @RequestParam(value = "goodPhoto") String goodPhoto,
            @RequestParam(value = "goodCost") int goodCost,
            @RequestParam(value = "goodContent") String goodContent,
                           HttpSession session) {
        User currentUser = userService.getCurrentUser(session);
        if(currentUser == null) {
            return ReturnData.result(-2, "未登录!", null);
        }
        Good good = goodService.getGoodById(goodId);
        good.setTitle(goodTitle);
        good.setIntro(goodIntro);
        good.setPhoto(goodPhoto);
        good.setCost(goodCost);
        good.setContent(goodContent);
        int row = goodService.editGood(good);
        if(row == 1) {
            return ReturnData.result(0, "编辑商品成功!", null);
        } else {
            return ReturnData.result(-1, "编辑商品失败!", null);
        }
    }
}
