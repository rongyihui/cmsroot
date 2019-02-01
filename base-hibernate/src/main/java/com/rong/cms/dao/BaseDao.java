package com.rong.cms.dao;

import com.rong.cms.model.Pager;
import com.rong.cms.model.SystemContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings("unchecked")
public class BaseDao<T> implements IBaseDao<T> {
    private Class clz;

    @Autowired()
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T add(T t) {
        getSession().save(t);
        return t;
    }

    @Override
    public Object addByObject(Object obj) {
        getSession().save(obj);
        return obj;
    }

    @Override
    public void delete(int id) {
        getSession().delete(this.load(id));
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public void updateByObject(Object obj) {
        getSession().update(obj);
    }


    @Override
    public T load(int id) {
        return getSession().load(getClz(), id);
    }


    
    public List listObj(String hql, Object[] args, Map<String, Object> alias) {
        hql = changeSortHql(hql);
        Query q = this.getSession().createQuery(hql);
        return setParametersQuery(q, args, alias).getResultList();
    }

    
    public List listObj(String hql, Object[] args) {
        return listObj(hql, args, null);
    }

    
    public List listObj(String hql, Object arg) {
        return listObj(hql, new Object[]{arg}, null);
    }

    
    public List listObj(String hql, Object arg, Map<String, Object> alias) {
        return listObj(hql, new Object[]{arg}, alias);
    }

    
    public List listObj(String hql, Map<String, Object> alias) {
        return this.listObj(hql, null, alias);
    }

    
    public List listObj(String hql) {
        return this.listObj(hql, null, null);
    }

    private String getCountHql(String chql) {
        String s = chql.substring(0, chql.indexOf("from"));
        if (s == null || "".equals(s)) {
            chql = "select count(*) " + chql;
        } else {
            chql = chql.replace(s, "select count(*) ");
        }
        chql = chql.replace("fetch", "");
        return chql;
    }

    
    public Pager find(String hql, Object[] args, Map<String, Object> alias) {
        Query cQ = setParametersQuery(getSession().createQuery(getCountHql(hql)), args, alias);
        long totalCount = (Long) cQ.getSingleResult();

        hql = changeSortHql(hql);
        Query q = setParametersQuery(getSession().createQuery(hql), args, alias);

        Pager pager = getPagerPro();
        List datas = q.setFirstResult(pager.getPageOffset()).setMaxResults(pager.getPageSize()).getResultList();
        pager.setCount(totalCount);
        pager.setData(datas);
        return pager;
    }

    //为分页添加属性
    private Pager getPagerPro() {
        Pager pager = new Pager();
        int pageSize = 20;
        try {
            pageSize = SystemContext.getPageSize();
        } catch (NullPointerException e) {
        }
        int pageOffset = 0;
        try {
            pageOffset = SystemContext.getPageOffset()-1;
        } catch (NullPointerException e) {
        }
        if (pageSize <= 0) pageSize = 20;
        if (pageOffset < 0) pageOffset = 0;
        pager.setPageSize(pageSize);
        pager.setPageOffset(pageOffset);
        return pager;
    }

    
    public Pager find(String hql, Object[] args) {
        return find(hql, args, null);
    }

    
    public Pager find(String hql, Object arg) {
        return find(hql, new Object[]{arg}, null);
    }

    
    public Pager find(String hql, Object arg, Map<String, Object> alias) {
        return find(hql, new Object[]{arg}, alias);
    }

    
    public Pager find(String hql, Map<String, Object> alias) {
        return find(hql, null, alias);
    }

    
    public Pager find(String hql) {
        return find(hql, null, null);
    }

    
    public Object queryByHql(String hql, Object[] args, Map<String, Object> alias) {
        List list = setParametersQuery(getSession().createQuery(hql), args, alias).getResultList();
        if (list.size() > 0) return list.get(0);
        else return null;
    }

    
    public Object queryByHql(String hql, Object[] args) {
        return queryByHql(hql, args, null);
    }

    
    public Object queryByHql(String hql, Object arg) {
        return queryByHql(hql, new Object[]{arg}, null);
    }

    
    public Object queryByHql(String hql, Object arg, Map<String, Object> alias) {
        return queryByHql(hql, new Object[]{arg}, alias);
    }

    
    public Object queryByHql(String hql, Map<String, Object> alias) {
        return queryByHql(hql, null, alias);
    }

    
    public Object queryByHql(String hql) {
        return queryByHql(hql, null, null);
    }

    
    public void excuteByHql(String hql, Object[] args) {
        setParametersQuery(getSession().createQuery(hql), args, null).executeUpdate();
    }

    
    public void excuteByHql(String hql, Object arg) {
        excuteByHql(hql, new Object[]{arg});
    }

    
    public void excuteByHql(String hql) {
        excuteByHql(hql, null);
    }

    
    public List listBysql(String sql, Object[] args, Map<String, Object> alias, Class<Object> clz, boolean hasEntity) {
        return getSqlQuery(sql, args, alias, clz, hasEntity).list();
    }

    
    public List listBysql(String sql, Map<String, Object> alias, Class<Object> clz, boolean hasEntity) {
        return listBysql(sql, null, alias, clz, hasEntity);
    }

    
    public List listBysql(String sql, Object arg, Class<Object> clz, boolean hasEntity) {
        return listBysql(sql, new Object[]{arg}, null, clz, hasEntity);
    }

    
    public List listBysql(String sql, Class<Object> clz, boolean hasEntity) {
        return listBysql(sql, null, null, clz, hasEntity);
    }


    //通过原生sql获取
    private NativeQuery getSqlQuery(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        NativeQuery<Object> nq = getSession().createNativeQuery(changeSortHql(sql));
        nq = (NativeQuery<Object>) setParametersQuery(nq, args, alias);
        if (hasEntity) {
            nq.addEntity(clz);
        } else {
            nq.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(clz));
        }
        return nq;
    }

    
    public Pager findBysql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        //获取总数
        NativeQuery<Object> totalQ = getSession().createNativeQuery(getCountHql(sql));
        totalQ = (NativeQuery<Object>) setParametersQuery(totalQ, args, alias);
        long totalPage = ((BigInteger) totalQ.uniqueResult()).longValue();
        //获取分页数据
        List datas = getSqlQuery(sql, args, alias, clz, hasEntity).list();
        Pager pager = getPagerPro();
        pager.setCount(totalPage);
        pager.setData(datas);
        return pager;
    }

    
    public Pager findBysql(String sql, Object arg, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        return findBysql(sql,new Object[]{arg},alias,clz,hasEntity);
    }

    
    public Pager findBysql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        return findBysql(sql,null,alias,clz,hasEntity);
    }

    
    public Pager findBysql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {
        return findBysql(sql,args,null,clz,hasEntity);
    }

    
    public Pager findBysql(String sql, Object arg, Class<?> clz, boolean hasEntity) {
        return findBysql(sql,new Object[]{arg},null,clz,hasEntity);
    }

    
    public Pager findBysql(String sql, Class<?> clz, boolean hasEntity) {
        return findBysql(sql,null,null,clz,hasEntity);
    }

    //获取泛型的class
    private Class<T> getClz() {
        if (clz == null) {
            Type[] type = ((ParameterizedType)
                    getClass().getGenericSuperclass()).getActualTypeArguments();
            clz = (Class) type[0];
        }
        return clz;
    }

    //配置排序
    private String changeSortHql(String hql) {
        String order = SystemContext.getOrder();
        String sort = SystemContext.getSort();
        if (sort != null && !"".equals(sort)) {
            hql += " order by " + sort;
            if ("desc".equals(order)) {
                hql += " desc";
            }
        }
        return hql;
    }

    //别名和通配符设置值
    private Query setParametersQuery(Query q, Object[] args, Map<String, Object> alias) {
        //别名的设置值
        if (alias != null) {
            for (String key : alias.keySet()) {
                Object v = alias.get(key);
                if (v instanceof Collection) {
                    q.setParameterList(key, (Collection) v);
                } else {
                    q.setParameter(key, v);
                }
            }
        }
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                q.setParameter(i, args[i]);
            }
        }
        return q;
    }
}
