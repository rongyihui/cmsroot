package com.rong.cms.service;


import com.rong.cms.model.Pager;
import com.rong.cms.model.Role;

import java.util.List;

public interface IRoleService {
    void add(Role role);
    void delete(int id);
    Role load(int id);
    void update(Role role);

    List<Role> listRole();
    Pager findRole();

    /**
     * 删除角色中的用户
     * @param rid 角色id
     */
    void deleteRoleUsers(int rid);
}
