package com.rong.cms.dao;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class ChannelDaoTest extends BaseDaoTest {

    @Resource
    private IChannelDao channelDao;

    /**
     * 指明进行数据库中哪个表的操作，并且需要创建配置文件data/t_xxx.xml
     */
    @BeforeClass
    public static void init() {
        tableName = "t_channel";
        tableTestName = "t_channel_test";
    }

    @Override
    public void add() {

    }
    @Test
    public void listByParent(){
        System.out.println(channelDao);
    }
    @Test
    public void getMaxPriorityByParent(){

    }
}
