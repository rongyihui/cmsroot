package com.rong.cms.controller;

import com.rong.cms.dto.UserDto;
import com.rong.cms.exception.CmsException;
import com.rong.cms.model.Pager;
import com.rong.cms.model.Role;
import com.rong.cms.model.User;
import com.rong.cms.service.IGroupService;
import com.rong.cms.service.IRoleService;
import com.rong.cms.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
//@SessionAttributes(value = {"user"})  这种每份都会存user，效率不高
public class UserController {

    private IUserService userService;
    private IGroupService groupService;
    private IRoleService roleService;

    @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    @Resource
    public void setGroupService(IGroupService groupService) {
        this.groupService = groupService;
    }
    @Resource
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public Pager list() {
        return userService.findUser();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listAll(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                          @RequestParam(value = "limit", defaultValue = "15", required = false) Integer limit) {
        return "admin/list";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "success";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User showUser(@PathVariable("id") Integer id) {
        User user = userService.load(id);
        return user;
    }

    private void initAddUser(Model model){
        model.addAttribute("roles",roleService.findRole());
        model.addAttribute("groups",groupService.findGroup());
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String addInput(Model model) {
        model.addAttribute("user",new UserDto());
        initAddUser(model);
        return "admin/add";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String add(@Valid UserDto userDto,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            throw new CmsException("非法操作");
        }
        userService.add(userDto.getUser(),userDto.getRoleIds(),userDto.getGroupIds());
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.GET)
    public String updateInput(@PathVariable("id") Integer id ,Model model) {
        User u =  userService.load(id);
        Integer[] roleIds = userService.listUserRoleId(id);
        Integer[] groupIds = userService.listUserGroupId(id);
        model.addAttribute("user",new UserDto(u,roleIds,groupIds));
        initAddUser(model);
        return "admin/update";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String update(@Valid UserDto userDto,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            List<ObjectError> errors= bindingResult.getAllErrors();
            for (ObjectError o :errors){
                System.out.println(o.getObjectName()+":"+o.getDefaultMessage());
            }
            throw new CmsException("非法操作");
        }
        userService.update(userDto.getUser(),userDto.getRoleIds(),userDto.getGroupIds());
        return "redirect:/admin/users";
    }
/*

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String addInput() {
        return "admin/add";
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String add() {
        return "redirect:/users";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String updateInput(@PathVariable("id") Integer id) {
        return "admin/update";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String update() {
        return "redirect:/users";
    }

    //异常处理,可以指定捕获的异常
    @ExceptionHandler(NullPointerException.class)
    public String handleException(Exception ex,Model model){
        model.addAttribute("exception",ex);
        return "error";
    }

    public String fileUpload(String desc, MultipartFile file){
        return "";
    }*/
}
