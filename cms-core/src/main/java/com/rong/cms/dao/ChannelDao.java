package com.rong.cms.dao;

import com.rong.cms.model.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelDao")
public class ChannelDao extends BaseDao<Channel> implements IChannelDao {
    @Override
    public List<Channel> listByParent(Integer pid) {
        String hql = "select c from Channel c where c.parent_Channel";
        hql = changeHqlByNull(hql,pid);
        return this.listObj(hql);
    }

    @Override
    public int getMaxPriorityByParent(Integer pid) {
        String hql = "select max(c.priority) from Channel c where c.parent_Channel";
        hql = changeHqlByNull(hql,pid);
        return (Integer) this.queryByHql(hql);
    }

    private String changeHqlByNull(String hql,Integer pid){
        if (pid==null){
            hql+= " is null";
        }else {
            hql+=".id ="+pid;
        }
        return hql;
    }
}
