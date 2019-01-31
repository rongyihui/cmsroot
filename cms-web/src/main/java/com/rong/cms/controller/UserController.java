package com.rong.cms.controller;

import com.rong.cms.model.Pager;
import com.rong.cms.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/user")
//@SessionAttributes(value = {"user"})  这种每份都会存user，效率不高
public class UserController {

    private IUserService userService;

    @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Pager list(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "1", required = false) Integer limit) {
        return userService.findUser();
    }

    @RequestMapping("/users")
    public String listPage() {
        return "admin/list";
    }
}
