package com.rong.cms.dao;

import com.rong.cms.model.Pager;
import com.rong.cms.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {
    @Override
    public List listRole() {
        return this.listObj("from Role");
    }

    @Override
    public Pager findRole() {
        return this.find("from Role");
    }

    @Override
    public void deleteRoleUsers(int rid) {
        String hql = "delete UserRole ur where ur.role.id=?0";
        this.excuteByHql(hql,rid);
    }

    @Override
    public List listUserRoleDto(int rid) {
        String hql = "select new com.rong.cms.dto.UserDto(u.id,u.nickname)" +
                " from UserRole ur left join ur.user u where ur.role.id=?0";
        return this.listObj(hql,rid);
    }
}
