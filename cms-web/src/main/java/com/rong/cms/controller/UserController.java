package com.rong.cms.controller;

import com.rong.cms.model.Pager;
import com.rong.cms.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private IUserService userService;

    @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Spring MVC中使用RequestMapping处理URL请求，
     * GET：表示查询；POST：表示添加操作
     * PUT：表示修改操作；DELETE：表示删除操作
     * consumes: 表示前端传过来的数据格式;
     * produces：表示返回数据格式
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public Pager list() {
        return userService.findUser();
    }
}
