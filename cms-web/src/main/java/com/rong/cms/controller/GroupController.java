package com.rong.cms.controller;

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

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    @ResponseBody
    public Pager list() {
        return groupService.findGroup();
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public String listAll(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                          @RequestParam(value = "limit", defaultValue = "15", required = false) Integer limit) {
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
        model.addAttribute("group", new Group());
        return "group/add";
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    @ResponseBody
    public String add(@Valid Group group, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            throw new CmsException("非法操作");
        }
        groupService.add(group);
        return "success";
    }

    @RequestMapping(value = "/group/update/{id}", method = RequestMethod.GET)
    public String updateInput(@PathVariable("id") Integer id, Model model) {
        Group group = groupService.load(id);
        model.addAttribute("group",group);
        return "group/update";
    }

    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@Valid Group group, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            throw new CmsException("非法操作");
        }
        groupService.update(group);
        return "success";
    }

    /**
     * 清空组中的所有用户
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/group/clean/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String clean (@PathVariable("id") Integer id){
        groupService.deleteGroupUsers(id);
        return "success";
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id,Model model) {
        model.addAttribute(groupService.load(id));
        model.addAttribute("users",groupService.listUserGroupDto(id));
        return "group/show";
    }
}
