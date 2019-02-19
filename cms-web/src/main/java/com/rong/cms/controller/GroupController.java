package com.rong.cms.controller;

import com.rong.cms.dto.UserDto;
import com.rong.cms.exception.CmsException;
import com.rong.cms.model.Group;
import com.rong.cms.model.Pager;
import com.rong.cms.service.IGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class GroupController {
    private IGroupService groupService;
    @Resource
    public void setGroupService(IGroupService groupService) {
        this.groupService = groupService;
    }
    @RequestMapping(value = "/group",method = RequestMethod.GET)
    @ResponseBody
    public Pager list(){
        return groupService.findGroup();
    }
    @RequestMapping(value = "/groups",method = RequestMethod.GET)
    public String listAll(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                          @RequestParam(value = "limit", defaultValue = "15", required = false) Integer limit){
        return "group/list";
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Integer id) {
        groupService.delete(id);
        return "success";
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.GET)
    public String addInput(Model model) {
        model.addAttribute("group",new Group());
        return "group/add";
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public String add(@Valid Group group, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            throw new CmsException("非法操作");
        }
        groupService.add(group);
        return "redirect:/admin/groups";
    }

    @RequestMapping(value = "/group/update/{id}", method = RequestMethod.GET)
    public String updateInput(@PathVariable("id") Integer id ,Model model) {
        //TODO

        return "group/update";
    }

    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    public String update(@Valid UserDto userDto,BindingResult bindingResult, Model model) {
        //TODO
        return "redirect:/admin/users";
    }
}
