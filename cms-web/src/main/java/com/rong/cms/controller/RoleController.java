package com.rong.cms.controller;


import com.rong.cms.model.Pager;
import com.rong.cms.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin")
public class RoleController {

    private IRoleService  roleService;
    @Resource
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    @ResponseBody
    public Pager list(){
        return roleService.findRole();
    }
}
