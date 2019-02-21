package com.rong.cms.controller;


import com.rong.cms.exception.CmsException;
import com.rong.cms.model.Group;
import com.rong.cms.model.Pager;
import com.rong.cms.model.Role;
import com.rong.cms.model.RoleType;
import com.rong.cms.service.IRoleService;
import com.rong.cms.util.EnumUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String listAll(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                          @RequestParam(value = "limit", defaultValue = "15", required = false) Integer limit) {
        return "role/list";
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Integer id) {
        roleService.delete(id);
        return "success";
    }

    @RequestMapping(value = "/role/add", method = RequestMethod.GET)
    public String addInput(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("types", EnumUtils.enum2String(RoleType.class));
        return "role/add";
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public String add(@Valid Role role, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            throw new CmsException("非法操作");
        }
        roleService.add(role);
        return "success";
    }

    @RequestMapping(value = "/role/update/{id}", method = RequestMethod.GET)
    public String updateInput(@PathVariable("id") Integer id, Model model) {
        Role role = roleService.load(id);
        model.addAttribute("role",role);
        model.addAttribute("types", EnumUtils.enum2String(RoleType.class));
        return "role/update";
    }

    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@Valid Role role, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            throw new CmsException("非法操作");
        }
        roleService.update(role);
        return "success";
    }

    /**
     * 清空组中的所有用户
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/role/clean/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String clean (@PathVariable("id") Integer id){
        roleService.deleteRoleUsers(id);
        return "success";
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id,Model model) {
        model.addAttribute(roleService.load(id));
        model.addAttribute("users",roleService.listUserRoleDto(id));
        return "role/show";
    }
}
