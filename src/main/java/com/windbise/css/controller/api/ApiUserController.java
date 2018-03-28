package com.windbise.css.controller.api;

import com.alibaba.fastjson.JSON;
import com.windbise.css.entity.User;
import com.windbise.css.service.UserService;
import com.windbise.css.util.ReturnData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by wangchengcheng on 2018/3/20.
 */
@Controller
@RequestMapping("/api")
public class ApiUserController {

    Logger logger = LoggerFactory.getLogger(ApiUserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        RedirectAttributes redirectAttributes, HttpSession session) {
//        password = new Md5Hash(password, Constants.MD5_SALT).toString();
        logger.info("login username:{},passowrd:{}", username, password);

        //开始调用shiro验证
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //获取当前的subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.error("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            logger.error("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        } catch (LockedAccountException lae) {
            logger.error("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.error("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.error("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下", ae);
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            User user = userService.getUserByUsername(username);
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            currentUser.getSession().setAttribute("user", user);
            session.setAttribute("userModel", user);
            return ReturnData.result(0, "登录成功", null);
        } else {
            token.clear();
            return ReturnData.result(-1, "用户名或者密码错误!", null);
        }
    }

    @RequestMapping("/v1/user")
    @ResponseBody
    public String userInfo(HttpSession session) {
        User currentUser = userService.getCurrentUser(session);
        if(currentUser != null) {
            currentUser.setPassword("");
        } else {
            currentUser = new User();
            currentUser.setId(-1);
        }
        return ReturnData.result(0, "获取当前用户信息成功!", JSON.toJSONString(currentUser));
    }

    @RequestMapping("/v1/logout")
    @ResponseBody
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }
}
