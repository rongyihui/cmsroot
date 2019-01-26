package com.rong.cms.dao;

import com.rong.cms.model.Group;
import org.springframework.stereotype.Repository;

@Repository("groupDao")
public class GroupDao extends BaseDao<Group> implements IGroupDao {
}
