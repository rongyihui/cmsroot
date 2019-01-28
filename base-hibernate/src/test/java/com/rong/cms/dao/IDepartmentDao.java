package com.rong.cms.dao;

import com.rong.cms.model.Department;
import com.rong.cms.model.Pager;

import java.util.List;
import java.util.Map;

public interface IDepartmentDao extends IBaseDao<Department> {

    /**
     * 不分页的查询，通过通配符？、：xx等查询
     *
     * @param hql
     * @param args
     * @param alias
     * @return
     */
    List listUser(String hql, Object[] args, Map<String, Object> alias);

    List listUser(String hql, Object[] args);

    List listUser(String hql, Object arg);

    List listUser(String hql, Object arg, Map<String, Object> alias);

    List listUser(String hql, Map<String, Object> alias);

    List listUser(String hql);
    /**
     * 分页查询，通过通配符=？
     *
     * @param hql
     * @param args
     * @return
     */
    Pager findUser(String hql, Object[] args, Map<String, Object> alias);

    Pager findUser(String hql, Object[] args);

    Pager findUser(String hql, Object arg);

    Pager findUser(String hql, Object arg, Map<String, Object> alias);

    Pager findUser(String hql, Map<String, Object> alias);

    Pager findUser(String hql);

    /**
     * 通过hql 查询对象
     *
     * @param hql
     * @param args
     * @param alias
     * @return
     */
    Object queryUserByHql(String hql, Object[] args, Map<String, Object> alias);

    Object queryUserByHql(String hql, Object[] args);

    Object queryUserByHql(String hql, Object arg);

    Object queryUserByHql(String hql, Object arg, Map<String, Object> alias);

    Object queryUserByHql(String hql, Map<String, Object> alias);

    Object queryUserByHql(String hql);

    /**
     * 通过hql 增删改对象
     *
     * @param hql hql
     * @param args
     */
    void excuteUserByHql(String hql, Object[] args);

    void excuteUserByHql(String hql, Object arg);

    void excuteUserByHql(String hql);

    /**
     * 根据sql查询对象，不包含关联对象
     *
     * @param sql       sql语句
     * @param args      条件
     * @param clz       实体对象
     * @param alias     通过别名查询
     * @param hasEntity 是否是一个hibernate管理的实体，例如DTO就需要通过setResultTransform
     * @return 分页对象
     */
    List listUserBysql(String sql, Object[] args, Map<String, Object> alias, Class<Object> clz, boolean hasEntity);

    List listUserBysql(String sql, Map<String, Object> alias, Class<Object> clz, boolean hasEntity);

    List listUserBysql(String sql, Object arg, Class<Object> clz, boolean hasEntity);

    List listUserBysql(String sql, Class<Object> clz, boolean hasEntity);

    /**
     * 根据sql分页查询对象，不包含关联对象
     *
     * @param sql       sql语句
     * @param args      条件
     * @param clz       实体对象
     * @param alias     通过别名查询
     * @param hasEntity 是否是一个hibernate管理的实体，例如DTO就需要通过setResultTransform
     * @return 分页对象
     */
    Pager findUserBysql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity);

    Pager findUserBysql(String sql, Object arg, Map<String, Object> alias, Class<?> clz, boolean hasEntity);

    Pager findUserBysql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity);

    Pager findUserBysql(String sql, Object[] args, Class<?> clz, boolean hasEntity);

    Pager findUserBysql(String sql, Object arg, Class<?> clz, boolean hasEntity);

    Pager findUserBysql(String sql, Class<?> clz, boolean hasEntity);
}
