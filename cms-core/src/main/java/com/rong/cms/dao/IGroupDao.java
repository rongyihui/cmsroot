package com.rong.cms.dao;

import com.rong.cms.model.Group;
import com.rong.cms.model.Pager;

import java.util.List;

public interface IGroupDao extends IBaseDao<Group> {
    List<Group> listGroup();
    Pager findGroup();
    void deleteGroupUsers(int gid);
    List listUserGroupDto(int gid);
}
