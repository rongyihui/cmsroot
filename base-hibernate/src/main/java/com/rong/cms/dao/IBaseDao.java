package com.rong.cms.dao;


import com.rong.cms.model.Pager;

import java.util.List;
import java.util.Map;

/**
 * 公共dao，包含hibernate基本操作
 *
 * @param <T>
 */
public interface IBaseDao<T> {
    /**
     * 添加对象
     *
     * @param t
     * @return
     */
    public T add(T t);

    /**
     * 添加任意一个对象
     *
     * @param obj
     * @return
     */
    public Object addByObject(Object obj);

    /**
     * 删除一个对象
     *
     * @param id
     */
    public void delete(int id);

    /**
     * 更新一个对象
     *
     * @param t
     */
    public void update(T t);

    /**
     * 更新任意一个对象
     *
     * @param obj
     */
    public void updateByObject(Object obj);

    public T load(int id);

    /**
     * 不分页的查询，通过通配符？、：xx等查询
     *
     * @param hql
     * @param args
     * @param alias
     * @return
     */
    public List<Object> listbyObj(String hql, Object[] args, Map<String, Object> alias);

    public List<Object> listbyObj(String hql, Object[] args);

    public List<Object> listbyObj(String hql, Object arg);

    public List<Object> listbyObj(String hql, Object arg, Map<String, Object> alias);

    public List<Object> listbyObj(String hql, Map<String, Object> alias);

    public List<Object> listbyObj(String hql);

    /**
     * 分页查询，通过通配符=？
     *
     * @param hql
     * @param args
     * @return
     */
    public Pager<Object> find(String hql, Object[] args, Map<String, Object> alias);

    public Pager<Object> find(String hql, Object[] args);

    public Pager<Object> find(String hql, Object arg);

    public Pager<Object> find(String hql, Object arg, Map<String, Object> alias);

    public Pager<Object> find(String hql, Map<String, Object> alias);

    public Pager<Object> find(String hql);

    /**
     * 通过hql 查询对象
     *
     * @param hql
     * @param args
     * @param alias
     * @return
     */
    public Object queryByHql(String hql, Object[] args, Map<String, Object> alias);

    public Object queryByHql(String hql, Object[] args);

    public Object queryByHql(String hql, Object arg);

    public Object queryByHql(String hql, Object arg, Map<String, Object> alias);

    public Object queryByHql(String hql, Map<String, Object> alias);

    public Object queryByHql(String hql);

    /**
     * 通过hql 增删改对象
     *
     * @param hql hql
     * @param args
     */
    public void excuteByHql(String hql, Object[] args);

    public void excuteByHql(String hql, Object arg);

    public void excuteByHql(String hql);

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
    public List<Object> listBysql(String sql, Object[] args, Map<String, Object> alias, Class<Object> clz, boolean hasEntity);

    public List<Object> listBysql(String sql, Map<String, Object> alias, Class<Object> clz, boolean hasEntity);

    public List<Object> listBysql(String sql, Object arg, Class<Object> clz, boolean hasEntity);

    public List<Object> listBysql(String sql, Class<Object> clz, boolean hasEntity);

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
    public Pager<Object> findBysql(String sql, Object[] args, Map<String, Object> alias, Class<Object> clz, boolean hasEntity);

    public Pager<Object> findBysql(String sql, Map<String, Object> alias, Class<Object> clz, boolean hasEntity);

    public Pager<Object> findBysql(String sql, Object args, Class<Object> clz, boolean hasEntity);

    public Pager<Object> findBysql(String sql, Class<Object> clz, boolean hasEntity);
}
