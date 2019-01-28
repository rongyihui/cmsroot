package com.rong.cms.dao;


import com.rong.cms.model.Role;
import com.rong.cms.model.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class UserDaoTest extends BaseDaoTest{

    @Resource(name = "userDao")
    private UserDao userDao;

    /**
     * 指明进行数据库中哪个表的操作，并且需要创建配置文件data/t_xxx.xml
     */
    @BeforeClass
    public static void init() {
        tableName ="t_user";
        tableTestName ="t_user_test";
    }

    @Test
    public void add() {
        User user = new User("admin","123","管理员"
                ,"5418@qq.com","12345678901","0"
                ,new Date(),new Date());
        userDao.add(user);
        Assert.assertEquals("用户添加失败",user.getNickname(),userDao.load(user.getId()).getNickname());
    }
    @Test
    public void listUserRoles(){
        List<Role> roles = userDao.listUserRoles(2);
        Assert.assertEquals("list指定用户角色列表查询失败",3,roles.size());
    }
}