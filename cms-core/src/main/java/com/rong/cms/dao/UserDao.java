package com.rong.cms.dao;

import com.rong.cms.exception.CmsException;
import com.rong.cms.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
@SuppressWarnings("unchecked")
public class UserDao extends BaseDao<User> implements IUserDao<User> {
    @Override
    public List<Role> listUserRoles(int userId) {
        String hql = "select r from UserRole ur right join ur.role r" +
                " left join ur.user u where u.id=?0";
        return this.listObj(hql, userId);
    }

    @Override
    public List<Group> listUserGroups(int userId) {
        String hql = "select ug.group from UserGroup ug where ug.user.id=?0";
        return this.listObj(hql, userId);
    }

    @Override
    public UserRole loadUserRole(int userId, int roleId) {
        String hql = "select ur from UserRole ur left join fetch ur.user u" +
                " left join fetch ur.role r where u.id=?0 and r.id=?1";
        return (UserRole) this.queryByHql(hql, new Object[]{userId, roleId});
    }

    @Override
    public UserGroup loadUserGroup(int userId, int groupId) {
        String hql = "select ug from UserGroup ug left join fetch ug.user u" +
                " left join fetch ug.group g where u.id=?0 and g.id=?1";
        return (UserGroup) this.queryByHql(hql, new Object[]{userId, groupId});
    }

    @Override
    public User loadByUsername(String name) {
        String hql = "from User where username=?0";
        return (User) this.queryByHql(hql, name);
    }

    @Override
    public List<User> listRoleUsers(int roleId) {
        String hql = "select ur.user from UserRole ur where ur.role.id=?0";
        return this.listObj(hql, roleId);
    }

    @Override
    public List<User> listRoleUsers(RoleType roleType) {
        String hql = "select ur.user from UserRole ur where ur.role.roleType=?0";
        return this.listObj(hql, roleType);
    }

    @Override
    public List<User> listGroupUsers(int groupId) {
        String hql = "select ug.user from UserGroup ug where ug.group.id=?0";
        return this.listObj(hql, groupId);
    }

    @Override
    public void addUserRole(User user, Role role) {
        UserRole userRole = this.loadUserRole(user.getId(), role.getId());
        if (userRole != null) return;
        userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        addByObject(userRole);
    }

    @Override
    public void addUserGroup(User user, Group group) {
        UserGroup userGroup = this.loadUserGroup(user.getId(), group.getId());
        if (userGroup != null) return;
        userGroup = new UserGroup();
        userGroup.setGroup(group);
        userGroup.setUser(user);
        addByObject(userGroup);
    }
}
