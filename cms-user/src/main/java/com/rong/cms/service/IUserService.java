package com.rong.cms.service;

import com.rong.cms.model.Group;
import com.rong.cms.model.Pager;
import com.rong.cms.model.Role;
import com.rong.cms.model.User;

import java.util.List;

public interface IUserService {
    /**
     * 添加用户，判断用户名是否存在，不存在则抛出异常
     * @param user 用户
     * @param rids 角色
     * @param gids 组
     */
    void add(User user,Integer[] rids,Integer[] gids);

    /**
     * 删除用户，需要删除用户、角色、组的对应关系
     * 如果存在相关信息不能删除
     * @param id 用户id
     */
    void delete(int id);

    /**
     * 用户更新 角色、组的添加删除都需要改变关联表
     * @param user 用户
     * @param rids 角色
     * @param gids 组
     */
    void update(User user,Integer[] rids,Integer[] gids);

    /**
     * 更新用户的状态
     * @param id 用户
     */
    void updateStatus(int id,int status);

    /**
     * 获取用户列表
     */
    Pager findUser();
    /**
     * 获取用户信息，并获取角色、组
     */
    public User load(int id);

    /**
     * 查询用户所有的角色
     * @param id
     * @return
     */
    public List<Role> listUserRoles(int id);

    /**
     * 查询用户所有的组信息
     * @param id
     * @return
     */
    public List<Group> listUserGroup(int id);
}
