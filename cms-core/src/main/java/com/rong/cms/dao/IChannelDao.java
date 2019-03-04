package com.rong.cms.dao;

import com.rong.cms.dto.BaseTreeDto;
import com.rong.cms.model.Channel;

import java.util.List;

public interface IChannelDao extends IBaseDao<Channel> {
    List<BaseTreeDto> listAllTree(Integer pid, String rootName);

    List<Channel> listByParent(Integer pid);

    int getMaxPriorityByParent(Integer pid);
}
