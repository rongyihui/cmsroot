package com.rong.cms.service;

import com.rong.cms.dao.IChannelDao;
import com.rong.cms.dto.BaseTreeDto;
import com.rong.cms.exception.CmsException;
import com.rong.cms.model.Channel;
import com.rong.cms.model.SystemContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("channelService")
public class ChannelService implements IChannelService {

    private IChannelDao channelDao;
    @Resource
    public void setChannelDao(IChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    @Override
    public void add(Channel channel, Integer pid) {
        Integer maxPriority = channelDao.getMaxPriorityByParent(pid);
        if (pid != null && pid > 0) {
            Channel pc = channelDao.load(pid);
            if (pc == null) throw new CmsException("父栏目不存在");
            channel.setParentChannel(pc);
        }
        channel.setPriority(maxPriority);
        channelDao.add(channel);
    }

    @Override
    public void delete(int id) {
        //TODO 1.是否存在子类栏目
        List<Channel> cs = channelDao.listByParent(id);
        if (cs != null || cs.size() > 0) throw new CmsException("还存在子栏目无法删除");
        //TODO 2.是否存在文章
        //TODO 3.删除和组的关系
    }

    @Override
    public void update(Channel channel) {
        channelDao.update(channel);
    }

    @Override
    public Channel load(int id) {
        return channelDao.load(id);
    }


    //设置通过priority来进行排序
    private void setSort(){
        String sort = SystemContext.getSort();
        if (sort==null||"".equals(sort.trim())){
            SystemContext.setSort("priority");
        }
    }

    @Override
    public List<BaseTreeDto> listAllTree() {
        setSort();
        return channelDao.listAllTree();
    }

    @Override
    public List<Channel> listByParent(Integer pid) {
        setSort();
        return channelDao.listByParent(pid);
    }

    @Override
    public void cleanAllArticle(int id) {
        //TODO 实现文章模块后来实现
    }
}
