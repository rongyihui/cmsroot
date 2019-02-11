package com.rong.cms.dao;

import com.rong.cms.model.Pager;
import com.rong.cms.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {
    @Override
    public List<Role> listRole() {
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
}
