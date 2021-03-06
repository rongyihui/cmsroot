package com.rong.cms.dao;


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
public class GroupDaoTest extends BaseDaoTest {

    @Resource
    private IGroupDao groupDao;

    /**
     * 指明进行数据库中哪个表的操作，并且需要创建配置文件data/t_xxx.xml
     */
    @BeforeClass
    public static void init() {
        tableName = "t_user";
        tableTestName = "t_user_test";
    }

    @Override
    public void add() {
    }

    @Test
    public void listUserGroupDto(){
        int gid = 1;
        List nicknames = groupDao.listUserGroupDto(gid);
        Assert.assertEquals("用户组中所有用户名称失败", 2, nicknames.size());
    }
}
