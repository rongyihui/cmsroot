package com.rong.cms.controller;

import com.rong.cms.dto.BaseTreeDto;
import com.rong.cms.service.IChannelService;
import com.rong.cms.util.TreeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ChannelController {
    private IChannelService channelService;

    @Resource
    public void setChannelService(IChannelService channelService) {
        this.channelService = channelService;
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseTreeDto> treeList() {
        List<BaseTreeDto> datas = channelService.listAllTree();
        List<BaseTreeDto> trees = new TreeUtil<>().tree2List(datas);
        return trees;
    }

    @RequestMapping(value = "/channel", method = RequestMethod.GET)
    public String lists() {
        return "channel/list";
    }
}
