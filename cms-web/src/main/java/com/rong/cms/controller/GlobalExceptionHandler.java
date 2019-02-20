package com.rong.cms.controller;

import com.rong.cms.exception.CmsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CmsException.class)
    @ResponseBody
    public CmsException getCmsException(CmsException ce){
        return ce;
    }

}
