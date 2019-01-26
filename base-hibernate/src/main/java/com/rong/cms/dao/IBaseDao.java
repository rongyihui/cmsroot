package com.rong.cms.dao;


import com.rong.cms.model.Pager;

import java.util.List;
import java.util.Map;

/**
 * 公共dao，包含hibernate基本操作
 *
 * @param
 */
public interface IBaseDao<T> {
    /**
     * 添加对象
     *
     * @param t 需要添加的对象
     * @return 返回添加的这个对象，方便操作
     */
    T add(T t);

    /**
     * 添加任意一个对象
     *
     * @param obj 需要添加的任意对象
     * @return 返回添加的这个对象，方便操作
     */
    Object addByObject(Object obj);

    /**
     * 删除一个对象
     *
     * @param id
     */
    void delete(int id);

    /**
     * 更新一个对象
     *
     * @param t 需要更新的对象
     */
    void update(T t);

    /**
     * 更新任意一个对象
     *
     * @param obj
     */
    void updateByObject(Object obj);

    T load(int id);

    /**
     * 不分页的查询，通过通配符？、：xx等查询
     *
     * @param hql
     * @param args
     * @param alias
     * @return
     */
    List listbyObj(String hql, Object[] args, Map<String, Object> alias);

    List listbyObj(String hql, Object[] args);

    List listbyObj(String hql, Object arg);

    List listbyObj(String hql, Object arg, Map<String, Object> alias);

    List listbyObj(String hql, Map<String, Object> alias);

    List listbyObj(String hql);

    /**
     * 分页查询，通过通配符=？
     *
     * @param hql
     * @param args
     * @return
     */
    Pager find(String hql, Object[] args, Map<String, Object> alias);

    Pager find(String hql, Object[] args);

    Pager find(String hql, Object arg);

    Pager find(String hql, Object arg, Map<String, Object> alias);

    Pager find(String hql, Map<String, Object> alias);

    Pager find(String hql);

    /**
     * 通过hql 查询对象
     *
     * @param hql
     * @param args
     * @param alias
     * @return
     */
    Object queryByHql(String hql, Object[] args, Map<String, Object> alias);

    Object queryByHql(String hql, Object[] args);

    Object queryByHql(String hql, Object arg);

    Object queryByHql(String hql, Object arg, Map<String, Object> alias);

    Object queryByHql(String hql, Map<String, Object> alias);

    Object queryByHql(String hql);

    /**
     * 通过hql 增删改对象
     *
     * @param hql hql
     * @param args
     */
    void excuteByHql(String hql, Object[] args);

    void excuteByHql(String hql, Object arg);

    void excuteByHql(String hql);

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
    List listBysql(String sql, Object[] args, Map<String, Object> alias, Class<Object> clz, boolean hasEntity);

    List listBysql(String sql, Map<String, Object> alias, Class<Object> clz, boolean hasEntity);

    List listBysql(String sql, Object arg, Class<Object> clz, boolean hasEntity);

    List listBysql(String sql, Class<Object> clz, boolean hasEntity);

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
    Pager findBysql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity);

    Pager findBysql(String sql, Object arg, Map<String, Object> alias, Class<?> clz, boolean hasEntity);

    Pager findBysql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity);

    Pager findBysql(String sql, Object[] args, Class<?> clz, boolean hasEntity);

    Pager findBysql(String sql, Object arg, Class<?> clz, boolean hasEntity);

    Pager findBysql(String sql, Class<?> clz, boolean hasEntity);
}
