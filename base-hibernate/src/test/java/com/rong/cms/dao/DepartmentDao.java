package com.rong.cms.dao;

import com.rong.cms.model.Department;
import com.rong.cms.model.Pager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("departmentDao")
public class DepartmentDao extends BaseDao<Department> implements IDepartmentDao{
    @Override
    public List listUser(String hql, Object[] args, Map<String, Object> alias) {
        return super.listObj(hql,args,alias);
    }

    @Override
    public List listUser(String hql, Object[] args) {
        return super.listObj(hql,args);
    }

    @Override
    public List listUser(String hql, Object arg) {
        return super.listObj(hql,arg);
    }

    @Override
    public List listUser(String hql, Object arg, Map<String, Object> alias) {
        return super.listObj(hql,arg,alias);
    }

    @Override
    public List listUser(String hql, Map<String, Object> alias) {
        return super.listObj(hql,alias);
    }

    @Override
    public List listUser(String hql) {
        return super.listObj(hql);
    }

    @Override
    public Pager findUser(String hql, Object[] args, Map<String, Object> alias) {
        return super.find(hql,args,alias);
    }

    @Override
    public Pager findUser(String hql, Object[] args) {
        return super.find(hql,args);
    }

    @Override
    public Pager findUser(String hql, Object arg) {
        return super.find(hql,arg);
    }

    @Override
    public Pager findUser(String hql, Object arg, Map<String, Object> alias) {
        return super.find(hql,arg,alias);
    }

    @Override
    public Pager findUser(String hql, Map<String, Object> alias) {
        return super.find(hql,alias);
    }

    @Override
    public Pager findUser(String hql) {
        return super.find(hql);
    }

    @Override
    public Object queryUserByHql(String hql, Object[] args, Map<String, Object> alias) {
        return super.queryByHql(hql,args,alias);
    }

    @Override
    public Object queryUserByHql(String hql, Object[] args) {
        return super.queryByHql(hql,args);
    }

    @Override
    public Object queryUserByHql(String hql, Object arg) {
        return super.queryByHql(hql,arg);
    }

    @Override
    public Object queryUserByHql(String hql, Object arg, Map<String, Object> alias) {
        return super.queryByHql(hql,arg,alias);
    }

    @Override
    public Object queryUserByHql(String hql, Map<String, Object> alias) {
        return super.queryByHql(hql,alias);
    }

    @Override
    public Object queryUserByHql(String hql) {
        return super.queryByHql(hql);
    }

    @Override
    public void excuteUserByHql(String hql, Object[] args) {
        super.excuteByHql(hql,args);
    }

    @Override
    public void excuteUserByHql(String hql, Object arg) {
        super.excuteByHql(hql,arg);
    }

    @Override
    public void excuteUserByHql(String hql) {
        super.excuteByHql(hql);
    }

    @Override
    public List listUserBysql(String sql, Object[] args, Map<String, Object> alias, Class<Object> clz, boolean hasEntity) {
        return super.listBysql(sql,args,alias,clz,hasEntity);
    }

    @Override
    public List listUserBysql(String sql, Map<String, Object> alias, Class<Object> clz, boolean hasEntity) {
        return super.listBysql(sql,alias,clz,hasEntity);
    }

    @Override
    public List listUserBysql(String sql, Object arg, Class<Object> clz, boolean hasEntity) {
        return super.listBysql(sql,arg,clz,hasEntity);
    }

    @Override
    public List listUserBysql(String sql, Class<Object> clz, boolean hasEntity) {
        return super.listBysql(sql,clz,hasEntity);
    }

    @Override
    public Pager findUserBysql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        return super.findBysql(sql,args,alias,clz,hasEntity);
    }

    @Override
    public Pager findUserBysql(String sql, Object arg, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        return super.findBysql(sql,arg,alias,clz,hasEntity);
    }

    @Override
    public Pager findUserBysql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        return super.findBysql(sql,alias,clz,hasEntity);
    }

    @Override
    public Pager findUserBysql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {
        return super.findBysql(sql,args,clz,hasEntity);
    }

    @Override
    public Pager findUserBysql(String sql, Object arg, Class<?> clz, boolean hasEntity) {
        return super.findBysql(sql,arg,clz,hasEntity);
    }

    @Override
    public Pager findUserBysql(String sql, Class<?> clz, boolean hasEntity) {
        return super.findBysql(sql,clz,hasEntity);
    }
}
