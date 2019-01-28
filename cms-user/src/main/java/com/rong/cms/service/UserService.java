package com.rong.cms.service;

import com.rong.cms.dao.IGroupDao;
import com.rong.cms.dao.IRoleDao;
import com.rong.cms.dao.IUserDao;
import com.rong.cms.exception.CmsException;
import com.rong.cms.model.Group;
import com.rong.cms.model.Role;
import com.rong.cms.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserService implements IUserService {

    private IUserDao userDao;
    private IRoleDao roleDao;
    private IGroupDao groupDao;
    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
    @Resource
    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Resource
    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public IRoleDao getRoleDao() {
        return roleDao;
    }

    public IGroupDao getGroupDao() {
        return groupDao;
    }

    @Override
    public void add(User user, Integer[] rids, Integer[] gids) {
        User tu = userDao.loadByUsername(user.getUsername());
        if(tu!=null) throw new CmsException("用户名已存在");
        userDao.add(user);
        for (Integer rid:rids) {
            //1.判断角色是否存在，不存在要抛异常
            Role role = roleDao.load(rid);
            if (role==null) throw new CmsException("角色不存在");
            //存在，
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(User user, Integer[] rids, Integer[] gids) {

    }

    @Override
    public void updateStatus(int id) {

    }

    @Override
    public void finUser() {

    }

    @Override
    public User load(int id) {
        return null;
    }

    @Override
    public List<Role> listUserRoles(int id) {
        return null;
    }

    @Override
    public List<Group> listUserGroup(int id) {
        return null;
    }
}
