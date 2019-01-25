package com.rong.cms.dao;


import com.rong.cms.model.Department;
import org.hibernate.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class DepartmentDaoTest extends BaseDaoTest{

    @Resource(name = "departmentDao")
    private DepartmentDao departmentDao;

    /**
     * 指明进行数据库中哪个表的操作，并且需要创建配置文件data/t_xxx.xml
     */
    @BeforeClass
    public static void init() {
        tableName ="t_department";
    }

    @Test
    public void add() {
        Department dep = new Department("射击部", "1", 1);
        Assert.assertEquals("添加部门失败", dep, departmentDao.add(dep));
    }
    @Test
    public void load() {
        Department dep = departmentDao.load(1);
        Assert.assertEquals("load失败","管理部",dep.getDepName());
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
    public void listByObject(){
        List<Object> deps = departmentDao.listbyObj("from Department");
        Assert.assertEquals("list的size有误",3,deps.size());
    }
    @Test
    public void find(){

    }
}
