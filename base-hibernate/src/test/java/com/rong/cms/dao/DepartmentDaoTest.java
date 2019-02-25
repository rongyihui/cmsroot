package com.rong.cms.dao;


import com.rong.cms.model.Department;
import com.rong.cms.model.Pager;
import com.rong.cms.model.SystemContext;
import org.hibernate.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class DepartmentDaoTest extends BaseDaoTest{

    @Resource(name = "departmentDao")
    private IDepartmentDao departmentDao;

    /**
     * 指明进行数据库中哪个表的操作，并且需要创建配置文件data/t_xxx.xml
     */
    @BeforeClass
    public static void init() {
        tableName ="t_department";
        tableTestName ="t_department_test";
    }

    @Test
    public void add() {
        Department dep = new Department("射击部", "1", 1);
        Assert.assertEquals("添加部门失败", dep, departmentDao.add(dep));
    }
    @Test
    public void load() {
        Department dep = departmentDao.load(1);
        Assert.assertEquals("load失败","管理部2",dep.getDepName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void delete() {
        departmentDao.delete(1);
        Department department = departmentDao.load(1);
        System.out.println(department.getDepName());
    }
    @Test
    public void update(){
        Department dep = departmentDao.load(1);
        dep.setDepName("游戏部");
        departmentDao.update(dep);
        dep = departmentDao.load(1);
        Assert.assertEquals("更新失败","游戏部",dep.getDepName());
    }
    @Test
    public void listObject(){
        List deps = departmentDao.listUser("from Department");
        Assert.assertEquals("list的size有误",21,deps.size());
    }
    @Test
    public void find(){
        //通配符测试
        String hql = "from Department dep where dep.status=?0";
        Pager deps = departmentDao.findUser(hql,new Object[]{1});
        Assert.assertEquals("通配符？查询出错",new Long(3),deps.getCount());

        //别名测试
        Map<String,Object> alias = new HashMap<>();
        alias.put("status",1);
        hql = "from Department dep where dep.status=:status";
        deps = departmentDao.findUser(hql,alias);
        Assert.assertEquals("别名 查询出错",new Long(3),deps.getCount());

        //分页测试
        try {
            hql = "from Department";
            SystemContext.setPageOffset(2);
            deps = departmentDao.findUser(hql);
            Assert.assertEquals("分页出错",6,deps.getData().size());
        } finally {
            SystemContext.removePageOffset();
        }

        //别名测试list
        alias.clear();
        alias.put("id", Arrays.asList(1,2,3,5,6));
        hql = "from Department dep where dep.id in (:id)";
        deps = departmentDao.findUser(hql,alias);
        Assert.assertEquals("别名 查询出错",new Long(5),deps.getCount());

        //排序测试
        try {
            hql = "from Department";
            SystemContext.setOrder("desc");
            SystemContext.setSort("depName");
            deps = departmentDao.findUser(hql);
            Assert.assertEquals("排序出错","管理部9",((Department)deps.getData().get(0)).getDepName());
        } finally {
            SystemContext.removeOrder();
            SystemContext.removeSort();
        }
    }
    @Test
    public void findBySql(){
        String sql = "select * from t_department dep where dep.status=?0 ";
        Pager deps = departmentDao.findUserBysql(sql,new Object[]{1},null,Department.class,true);
        Assert.assertEquals("sql通配符？查询出错",new Long(3),deps.getCount());
    }
}
