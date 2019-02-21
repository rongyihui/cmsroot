package com.rong.cms.dao;

import com.rong.cms.model.Pager;
import com.rong.cms.model.Role;

import java.util.List;

public interface IRoleDao extends IBaseDao<Role> {
    List<Role> listRole();
    Pager findRole();

    void deleteRoleUsers(int rid);
    /**
     *
     * @param rid 角色id
     * @return Dto
     */
    List listUserRoleDto(int rid);
}
