package com.rong.cms.dao;

import com.rong.cms.dto.ChannelTreeDto;
import com.rong.cms.model.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelDao")
public class ChannelDao extends BaseDao<Channel> implements IChannelDao {

    @Override
    public List listAllTree(Integer pid,String rootName) {
        String sql = "select id,name,pid from t_channel";
        List ctd = this.listBysql(sql,ChannelTreeDto.class,false);
        ctd.add(new ChannelTreeDto(0,rootName,-1));
        return ctd;
    }

    @Override
    public List<Channel> listByParent(Integer pid) {
        String hql = "select c from Channel c where c.parentChannel";
        hql = changeHqlByNull(hql,pid);
        return this.listObj(hql);
    }

    @Override
    public int getMaxPriorityByParent(Integer pid) {
        String hql = "select max(c.priority) from Channel c where c.parentChannel";
        hql = changeHqlByNull(hql,pid);
        Object obj = this.queryByHql(hql);
        if (obj==null){
            return 0;
        }
        return (Integer)obj;
    }

    private String changeHqlByNull(String hql,Integer pid){
        if (pid==null||pid==0){
            hql+= " is null";
        }else {
            hql+=".id ="+pid;
        }
        return hql;
    }
}
