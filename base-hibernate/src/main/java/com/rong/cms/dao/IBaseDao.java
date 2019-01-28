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
}
