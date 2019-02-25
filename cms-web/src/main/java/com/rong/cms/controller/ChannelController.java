package com.rong.cms.controller;

import com.rong.cms.model.Channel;
import com.rong.cms.service.IChannelService;
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

    @RequestMapping(value = "/channel", method = RequestMethod.GET)
    @ResponseBody
    public List<Channel> list() {
        return channelService.listByParent(null);
    }
}
