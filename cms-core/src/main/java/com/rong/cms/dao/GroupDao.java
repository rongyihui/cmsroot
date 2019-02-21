package com.rong.cms.dao;

import com.rong.cms.model.Group;
import com.rong.cms.model.Pager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("groupDao")
public class GroupDao extends BaseDao<Group> implements IGroupDao {
    @Override
    public List<Group> listGroup() {
        return this.listObj("from Group");
    }

    @Override
    public Pager findGroup() {
        return this.find("from Group");
    }

    @Override
    public void deleteGroupUsers(int gid) {
        String hql = "delete UserGroup ug where ug.group.id=?0";
        this.excuteByHql(hql, gid);
    }

    @Override
    public List listUserGroupDto(int gid) {
        String hql = "select new com.rong.cms.dto.UserGroupDto(u.id,u.nickname)" +
                " from UserGroup ug left join ug.user u where ug.group.id=?0";
        return this.listObj(hql,gid);
    }
}
