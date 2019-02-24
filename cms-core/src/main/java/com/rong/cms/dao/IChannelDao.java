package com.rong.cms.dao;

import com.rong.cms.model.Channel;

import java.util.List;

public interface IChannelDao extends IBaseDao<Channel> {
    List<Channel> listByParent(Integer pid);

    int getMaxPriorityByParent(Integer pid);
}
