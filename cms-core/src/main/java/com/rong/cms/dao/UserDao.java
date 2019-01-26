package com.rong.cms.dao;

import com.rong.cms.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
@SuppressWarnings("unchecked")
public class UserDao extends BaseDao<User> implements IUserDao<User> {
    @Override
    public List<Role> listUserRoles(int userId) {
        String hql = "select ur.role from UserRole ur where ur.user.id=?0";
        return this.listbyObj(hql,userId);
    }

    @Override
    public List<Group> listUserGroups(int userId) {
        String hql = "select ug.group from UserGroup ug where ur.user.id=?0";
        return this.listbyObj(hql,userId);
    }

    @Override
    public UserRole loadUserRole(int userId, int roleId) {
        String hql = "select ur from UserRole ur where ur.user.id=?0 and ur.role.id=?1";
        return (UserRole)this.queryByHql(hql,new Object[]{userId,roleId});
    }

    @Override
    public UserGroup loadUserGroup(int userId, int groupId) {
        String hql = "select ug from UserGroup ug where ug.user.id=?0 and ug.group.id=?1";
        return (UserGroup)this.queryByHql(hql,new Object[]{userId,groupId});
    }

    @Override
    public User loadByName(String name) {
        String hql = "from User where username=?0";
        return (User)this.queryByHql(hql,name);
    }

    @Override
    public List<User> listRoleUsers(int roleId) {
        String hql = "select ur.user from UserRole ur where ur.role.id=?0";
        return this.listbyObj(hql,roleId);
    }

    @Override
    public List<User> listRoleUsers(RoleType roleType) {
        String hql = "select ur.user from UserRole ur where ur.role.roleType=?0";
        return this.listbyObj(hql,roleType);
    }

    @Override
    public List<User> listGroupUsers(int groupId) {
        String hql = "select ug.user from UserGroup ug where ug.group.id=?0";
        return this.listbyObj(hql,groupId);
    }
}
