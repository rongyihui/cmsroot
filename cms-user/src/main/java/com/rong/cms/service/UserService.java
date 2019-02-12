package com.rong.cms.service;

import com.rong.cms.dao.IGroupDao;
import com.rong.cms.dao.IRoleDao;
import com.rong.cms.dao.IUserDao;
import com.rong.cms.exception.CmsException;
import com.rong.cms.model.Group;
import com.rong.cms.model.Pager;
import com.rong.cms.model.Role;
import com.rong.cms.model.User;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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


    @Override
    public void add(User user, Integer[] rids, Integer[] gids) {
        User tu = userDao.loadByUsername(user.getUsername());
        if(tu!=null) throw new CmsException("用户名已存在");
        user.setCreateDate(new Date());
        userDao.add(user);
        //添加角色关联
        for (Integer rid:rids) {
            addRole(user,rid);
        }
        //添加用户关联
        for (Integer gid:gids) {
            addGroup(user,gid);
        }
    }

    private void addRole(User user,int rid){
        //1.判断角色是否存在，不存在要抛异常
        Role role = roleDao.load(rid);
        if (role==null) throw new CmsException("角色不存在");
        //存在
        userDao.addUserRole(user,role);
    }

    private void addGroup(User user,int gid){
        //1.判断组是否存在，不存在要抛异常
        Group group = groupDao.load(gid);
        if (group==null) throw new CmsException("组不存在");
        //存在
        userDao.addUserGroup(user,group);
    }

    @Override
    public void delete(int id) {
        //TODO 还需要判断其他和user关联的内容
        //删除用户关联表
        userDao.deleteUserRole(id);
        userDao.deleteUserGroup(id);
        userDao.delete(id);
    }

    @Override
    public void update(User user, Integer[] rids, Integer[] gids) {
        List<Integer> urids = userDao.listUserRolesId(user.getId());
        List<Integer> ugids = userDao.listUserGroupsId(user.getId());
        //添加选中的id
        for(Integer rid:rids) {
            if (!urids.contains(rid)){
                addRole(user,rid);
            }
        }
        for(Integer gid:gids) {
            if (!ugids.contains(gid)){
                addGroup(user,gid);
            }
        }
        //删除取消掉的id
        for(Integer urid:urids){
            if (!ArrayUtils.contains(rids,urid)){
                userDao.deleteUserRole(user.getId(),urid);
            }
        }
        for(Integer ugid:ugids){
            if (!ArrayUtils.contains(gids,ugid)){
                userDao.deleteUserGroup(user.getId(),ugid);
            }
        }
    }

    @Override
    public void updateStatus(int id,int status) {
        User u = userDao.load(id);
        if(u==null) throw new CmsException("用户不存在");
        u.setStatus(status);
        userDao.update(u);
    }

    @Override
    public Pager findUser() {
        return userDao.findUser();
    }

    @Override
    public User load(int id) {
        return userDao.load(id);
    }

    @Override
    public List<Role> listUserRoles(int id) {
        return userDao.listUserRoles(id);
    }

    @Override
    public List<Group> listUserGroup(int id) {
        return userDao.listUserGroups(id);
    }

}
