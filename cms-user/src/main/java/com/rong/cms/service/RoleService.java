package com.rong.cms.service;

import com.rong.cms.dao.IRoleDao;
import com.rong.cms.dao.IUserDao;
import com.rong.cms.exception.CmsException;
import com.rong.cms.model.Pager;
import com.rong.cms.model.Role;
import com.rong.cms.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("roleService")
public class RoleService implements IRoleService {

    private IRoleDao roleDao;
    private IUserDao userDao;
    @Resource
    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public void delete(int id) {
        List<User> users = userDao.listRoleUsers(id);
        if(users!=null&&users.size()>0) throw new CmsException("角色中还存在用户，无法删除");
        roleDao.delete(id);
    }

    @Override
    public Role load(int id) {
        return roleDao.load(id);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public List<Role> listRole() {
        return roleDao.listRole();
    }

    @Override
    public Pager findRole() {
        return roleDao.findRole();
    }

    @Override
    public void deleteRoleUsers(int rid) {
        roleDao.deleteRoleUsers(rid);
    }

    @Override
    public List listUserRoleDto(int rid) {
        return roleDao.listUserRoleDto(rid);
    }
}
