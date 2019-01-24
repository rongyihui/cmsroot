package com.rong.cms.dao;

import com.rong.cms.model.Department;
import org.springframework.stereotype.Repository;

@Repository("departmentDao")
public class DepartmentDao extends BaseDao<Department> implements IBaseDao<Department>{
}
