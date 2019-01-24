package com.rong.cms.dao;


import com.rong.cms.model.Department;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    //延迟加载的问题还没解决，
    public void delete() {
        departmentDao.delete(3);
        Department department = departmentDao.load(3);
        System.out.println(department.getDepName());
    }
    @Test
    @Transactional
    @Rollback
    //延迟加载的问题还没解决，
    public void update(){
        Department dep = departmentDao.load(1);
        dep.setDepName("游戏部");
        departmentDao.update(dep);
        dep = departmentDao.load(1);
        Assert.assertEquals("更新失败","游戏部",dep.getDepName());
    }
}
