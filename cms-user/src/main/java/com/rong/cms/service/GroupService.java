package com.rong.cms.service;

import com.rong.cms.dao.IGroupDao;
import com.rong.cms.dao.IUserDao;
import com.rong.cms.exception.CmsException;
import com.rong.cms.model.Group;
import com.rong.cms.model.Pager;
import com.rong.cms.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("groupService")
public class GroupService implements IGroupService {

    private IGroupDao groupDao;
    private IUserDao userDao;

    @Resource
    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(Group group) {
        groupDao.add(group);
    }

    @Override
    public void delete(int id) {
        List<User> users = userDao.listGroupUsers(id);
        if (users!=null&&users.size()>0) throw new CmsException("组中还有用户，无法删除");
        groupDao.delete(id);
    }

    @Override
    public Group load(int id) {
        return groupDao.load(id);
    }

    @Override
    public void update(Group group) {
        groupDao.update(group);
    }

    @Override
    public List<Group> listGroup() {
        return groupDao.listGroup();
    }

    @Override
    public Pager findGroup() {
        return groupDao.findGroup();
    }

    @Override
    public void deleteGroupUsers(int gid) {
        groupDao.deleteGroupUsers(gid);
    }
}
