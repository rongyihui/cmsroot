package com.rong.cms.service;


import com.rong.cms.dto.BaseTreeDto;
import com.rong.cms.model.Channel;

import java.util.List;

public interface IChannelService {
    void add(Channel channel,Integer pid);

    void delete(int id);

    void update(Channel channel);

    Channel load(int id);

    List<BaseTreeDto> listAllTree();

    /**
     * 通过父栏目查询所有的子栏目
     * @param pid 父栏目id
     * @return
     */
    List<Channel> listByParent(Integer pid);

    /**
     * 删除栏目中所有的文章
     * @param id 栏目id
     */
    void cleanAllArticle(int id);
}
