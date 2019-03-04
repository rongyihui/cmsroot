package com.rong.cms.dao;

import com.rong.cms.dto.BaseTreeDto;
import com.rong.cms.model.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelDao")
public class ChannelDao extends BaseDao<Channel> implements IChannelDao {

    @Override
    public List<BaseTreeDto> listAllTree() {
        String sql = "select id,name,pid from t_channel";
        List ctd = this.listBysql(sql, BaseTreeDto.class,false);
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
