package com.rong.cms.dao;

import com.rong.cms.model.*;

import java.util.List;

public interface IUserDao<User> extends IBaseDao<User>{
    /**
     * 对于某个用户获取他的role信息
     * @param userId 用户id
     * @return 一组role
     */
    List<Role> listUserRoles(int userId);

    /**
     * 对于某个用户获取他的group信息
     * @param userId 用户id
     * @return 一组group
     */
    List<Group> listUserGroups(int userId);

    /**
     * 通过一个用户和角色获取UserRole对象
     *
     * @param userId 用户
     * @param roleId 角色
     * @return UserRole对象
     */
    UserRole loadUserRole(int userId, int roleId);
    /**
     * 通过一个用户和组获取UserGroup对象
     *
     * @param userId 用户
     * @param groupId 组
     * @return UserGroup对象
     */
    UserGroup loadUserGroup(int userId, int groupId);

    /**
     * 通过用户名来获取User
     * @param name username
     * @return User对象
     */
    com.rong.cms.model.User loadByUsername(String name);

    /**
     * 通过角色id获取用户列表
     * @param roleId 角色id
     * @return List<User>对象
     */
    List<User> listRoleUsers(int roleId);

    /**
     * 通过角色类型获取一组用户对象
     * @param roleType 角色类型
     * @return List<User>对象
     */
    List<User> listRoleUsers(RoleType roleType);

    /**
     * 通过组id来查询所有用户列表
     * @param groupId 组id
     * @return
     */
    List<User> listGroupUsers(int groupId);

    /**
     * 添加用户、角色对象
     * @param user 用户
     * @param role 角色
     */
    void addUserRole(User user,Role role);
    /**
     * 添加用户、组对象
     * @param user 用户
     * @param group 组
     */
    void addUserGroup(User user,Group group);
}
