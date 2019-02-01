package com.rong.cms.controller;

import com.rong.cms.model.Pager;
import com.rong.cms.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin")
//@SessionAttributes(value = {"user"})  这种每份都会存user，效率不高
public class UserController {

    private IUserService userService;

    @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public Pager list(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                      @RequestParam(value = "limit", defaultValue = "1", required = false) Integer limit) {
        return userService.findUser();
    }

/*    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listAll(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "1", required = false) Integer limit) {
        return "admin/list";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        return "redirect:/users";
    }

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
