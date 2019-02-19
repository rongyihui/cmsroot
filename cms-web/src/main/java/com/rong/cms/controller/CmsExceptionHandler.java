package com.rong.cms.controller;

import com.rong.cms.exception.CmsException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmsExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mav = new ModelAndView();
        if(e instanceof CmsException){
            mav.setViewName("error");
            mav.addObject("ex", e);
            //System.out.println(e.getMessage());
            return mav;
        }
        return null;
    }
}
