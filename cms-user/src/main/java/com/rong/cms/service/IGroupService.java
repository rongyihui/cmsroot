package com.rong.cms.service;

import com.rong.cms.model.Group;
import com.rong.cms.model.Pager;

import java.util.List;

public interface IGroupService {
    void add(Group group);
    void delete(int id);
    Group load(int id);
    void update(Group group);

    List<Group> listGroup();
    Pager findGroup();

    /**
     * 删除组中的用户
     * @param gid 组id
     */
    void deleteGroupUsers(int gid);

    /**
     *
     * @param gid
     * @return
     */
    List listUserGroupDto(int gid);
}
