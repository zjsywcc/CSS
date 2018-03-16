package com.windbise.css.controller.core;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangchengcheng on 2018/3/1.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String NOT_FOUND_ERROR_VIEW = "error/404";
    private static final String UNAUTHORIZED_ERROR_VIEW = "error/401";
    public static final String INTERNAL_SERVER_ERROR_ERROR_VIEW = "error/500";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView notFoundErrorHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        switch(res.getStatus()) {
            case 404:
                mav.setViewName(NOT_FOUND_ERROR_VIEW);
                break;
            case 401:
                mav.setViewName(UNAUTHORIZED_ERROR_VIEW);
                break;
            case 500:
                mav.setViewName(INTERNAL_SERVER_ERROR_ERROR_VIEW);
                break;
            default:
                mav.setViewName(NOT_FOUND_ERROR_VIEW);
                break;
        }
        return mav;
    }


}
