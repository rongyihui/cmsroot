package com.rong.cms.dao;


import com.rong.cms.dto.BaseTreeDto;
import com.rong.cms.model.Channel;
import com.rong.cms.model.SystemContext;
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
        List<Channel> channels = channelDao.listByParent(null);
        Assert.assertEquals("子栏目总数获取失败",4,channels.size());
        Assert.assertEquals("子栏目获取失败",1,channels.get(0).getId());
        Assert.assertEquals("子栏目获取失败",5,channels.get(1).getId());
        Assert.assertEquals("子栏目获取失败",9,channels.get(2).getId());
        Assert.assertEquals("子栏目获取失败",13,channels.get(3).getId());
        channels = channelDao.listByParent(1);
        Assert.assertEquals("子栏目总数获取失败",3,channels.size());
        Assert.assertEquals("子栏目获取失败",2,channels.get(0).getId());
        Assert.assertEquals("子栏目获取失败",3,channels.get(1).getId());
        Assert.assertEquals("子栏目获取失败",4,channels.get(2).getId());
    }

    @Test
    public void listAllTree(){
        String sort = SystemContext.getSort();
        if (sort==null||"".equals(sort.trim())){
            SystemContext.setSort("priority");
        }
        List<BaseTreeDto> trees = channelDao.listAllTree();
        Assert.assertEquals("所有栏目获取失败",16,trees.size());
        //检测排序
        Assert.assertEquals("所有栏目获取失败",new Integer(1),trees.get(0).getId());
    }

    @Test
    public void getMaxPriorityByParent(){
        int max = channelDao.getMaxPriorityByParent(null);
        Assert.assertEquals("通过栏目获得最大排序栏目号",4,max);
        max = channelDao.getMaxPriorityByParent(1);
        Assert.assertEquals("通过栏目获得最大排序栏目号",3,max);
    }
}
