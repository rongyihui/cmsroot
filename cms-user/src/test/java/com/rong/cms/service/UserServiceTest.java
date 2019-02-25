package com.rong.cms.service;

import com.rong.cms.dao.IGroupDao;
import com.rong.cms.dao.IRoleDao;
import com.rong.cms.dao.IUserDao;
import com.rong.cms.exception.CmsException;
import com.rong.cms.model.Group;
import com.rong.cms.model.Pager;
import com.rong.cms.model.Role;
import com.rong.cms.model.User;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class UserServiceTest {
    @Resource
    private IUserService userService;
    @Resource
    private IRoleDao roleDao;
    @Resource
    private IUserDao userDao;
    @Resource
    private IGroupDao groupDao;

    private User baseUser;

    @Before
    public void setUp() {
        baseUser = new User("admin"
                , "123", "管理员", "5418@qq.com"
                , "1233", 1, new Date(), new Date());
    }

    @Test
    public void testDelete() {
        EasyMock.reset(userDao);
        int uid = 2;
        userDao.deleteUserRole(uid);
        EasyMock.expectLastCall();
        userDao.deleteUserGroup(uid);
        EasyMock.expectLastCall();
        userDao.delete(uid);
        EasyMock.replay(userDao);
        userService.delete(uid);
        EasyMock.verify(userDao);
    }
    @Test(expected = CmsException.class)
    public void testUpdateStatusIsNull() {
        EasyMock.reset(userDao);
        int uid = 1;
        int status = 0;
        EasyMock.expect(userDao.load(uid)).andReturn(null);
        userDao.update(baseUser);
        EasyMock.replay(userDao);
        userService.updateStatus(uid, status);
        Assert.assertEquals("用户状态修改失败", status, baseUser.getStatus());
        EasyMock.verify(userDao);
    }
    @Test
    public void testUpdateStatusNotNull() {
        EasyMock.reset(userDao);
        int uid = 1;
        int status = 0;
        EasyMock.expect(userDao.load(uid)).andReturn(baseUser);
        userDao.update(baseUser);
        EasyMock.replay(userDao);
        userService.updateStatus(uid, status);
        Assert.assertEquals("用户状态修改失败", status, baseUser.getStatus());
        EasyMock.verify(userDao);
    }

    @Test
    public void testFindUser() {
        EasyMock.reset(userDao);
        EasyMock.expect(userDao.findUser()).andReturn(new Pager());
        EasyMock.replay(userDao);
        userService.findUser();
        EasyMock.verify(userDao);
    }
    @Test
    public void testLoad(){
        EasyMock.reset(userDao);
        int uid = 1;
        EasyMock.expect(userDao.load(uid)).andReturn(baseUser);
        EasyMock.replay(userDao);
        userService.load(uid);
        EasyMock.verify(userDao);
    }
    @Test(expected = CmsException.class)
    public void testAddHasUser(){
        EasyMock.reset(userDao,roleDao,groupDao);
        Role role = new Role();
        Group group = new Group();
        role.setId(1);
        group.setId(2);
        Integer[] rids={1,2};
        Integer[] gids={2,3};
        //用户存在
        EasyMock.expect(userDao.loadByUsername("admin")).andReturn(baseUser);
        EasyMock.expect(userDao.add(baseUser)).andReturn(baseUser);

        EasyMock.expect(roleDao.load(EasyMock.gt(0))).andReturn(role).times(rids.length);
        userDao.addUserRole(baseUser,role);
        EasyMock.expectLastCall().times(rids.length);
        /*
        对详细的流程进行捕获，上面.times可以模拟执行几次，两种方式都能解决
        EasyMock.expectLastCall();
        role.setId(2);
        EasyMock.expect(roleDao.load(rids[1])).andReturn(role);
        userDao.addUserRole(baseUser,role);
        EasyMock.expectLastCall();
        */
        //EasyMock.gt(0)表示大于0的都可以，动态参数
        EasyMock.expect(groupDao.load(EasyMock.gt(0))).andReturn(group).times(gids.length);
        userDao.addUserGroup(baseUser,group);
        EasyMock.expectLastCall().times(gids.length);
        /*
        EasyMock.expectLastCall();
        role.setId(3);
        EasyMock.expect(groupDao.load(gids[1])).andReturn(group);
        userDao.addUserGroup(baseUser,group);
        EasyMock.expectLastCall();
        */

        EasyMock.replay(userDao,roleDao,groupDao);
        userService.add(baseUser,rids,gids);
        EasyMock.verify(userDao,roleDao,groupDao);
    }

    @Test(expected = CmsException.class)
    public void testAddNotHasRole(){
        EasyMock.reset(userDao,roleDao,groupDao);
        Role role = new Role();
        Group group = new Group();
        role.setId(1);
        group.setId(2);
        Integer[] rids={1,2};
        Integer[] gids={2,3};
        EasyMock.expect(userDao.loadByUsername("admin")).andReturn(null);
        EasyMock.expect(userDao.add(baseUser)).andReturn(baseUser);
        //角色不存在
        EasyMock.expect(roleDao.load(EasyMock.gt(0))).andReturn(null).times(rids.length);
        userDao.addUserRole(baseUser,role);
        EasyMock.expectLastCall().times(rids.length);
        EasyMock.expect(groupDao.load(EasyMock.gt(0))).andReturn(group).times(gids.length);
        userDao.addUserGroup(baseUser,group);
        EasyMock.expectLastCall().times(gids.length);
        EasyMock.replay(userDao,roleDao,groupDao);
        userService.add(baseUser,rids,gids);
        EasyMock.verify(userDao,roleDao,groupDao);
    }

    @Test(expected = CmsException.class)
    public void testAddNotHasGroup(){
        EasyMock.reset(userDao,roleDao,groupDao);
        Role role = new Role();
        Group group = new Group();
        role.setId(1);
        group.setId(2);
        Integer[] rids={1,2};
        Integer[] gids={2,3};
        EasyMock.expect(userDao.loadByUsername("admin")).andReturn(null);
        EasyMock.expect(userDao.add(baseUser)).andReturn(baseUser);
        EasyMock.expect(roleDao.load(EasyMock.gt(0))).andReturn(role).times(rids.length);
        userDao.addUserRole(baseUser,role);
        EasyMock.expectLastCall().times(rids.length);
        //组不存在
        EasyMock.expect(groupDao.load(EasyMock.gt(0))).andReturn(null).times(gids.length);
        userDao.addUserGroup(baseUser,group);
        EasyMock.expectLastCall().times(gids.length);
        EasyMock.replay(userDao,roleDao,groupDao);
        userService.add(baseUser,rids,gids);
        EasyMock.verify(userDao,roleDao,groupDao);
    }

    @Test
    public void testAddNotHasUser(){
        EasyMock.reset(userDao,roleDao,groupDao);
        Role role = new Role();
        Group group = new Group();
        role.setId(1);
        group.setId(2);
        Integer[] rids={1,2};
        Integer[] gids={2,3};
        EasyMock.expect(userDao.loadByUsername("admin")).andReturn(null);
        EasyMock.expect(userDao.add(baseUser)).andReturn(baseUser);
        EasyMock.expect(roleDao.load(EasyMock.gt(0))).andReturn(role).times(rids.length);
        userDao.addUserRole(baseUser,role);
        EasyMock.expectLastCall().times(rids.length);
        EasyMock.expect(groupDao.load(EasyMock.gt(0))).andReturn(group).times(gids.length);
        userDao.addUserGroup(baseUser,group);
        EasyMock.expectLastCall().times(gids.length);
        EasyMock.replay(userDao,roleDao,groupDao);
        userService.add(baseUser,rids,gids);
        EasyMock.verify(userDao,roleDao,groupDao);
    }
    @Test
    public void testUpdateUser(){
        EasyMock.reset(userDao,roleDao,groupDao);
        Role role = new Role();
        Group group = new Group();
        Integer[] rids={1,2};
        Integer[] gids={1,2};
        List<Integer> urids = Arrays.asList(2,3);
        List<Integer> ugids = Arrays.asList(1,3);
        userDao.update(baseUser);
        EasyMock.expectLastCall();
        EasyMock.expect(userDao.listUserRolesId(baseUser.getId())).andReturn(urids);
        EasyMock.expect(userDao.listUserGroupsId(baseUser.getId())).andReturn(ugids);
        //验证了添加新关联
        EasyMock.expect(roleDao.load(1)).andReturn(role);
        userDao.addUserRole(baseUser,role);
        EasyMock.expectLastCall();
        EasyMock.expect(groupDao.load(2)).andReturn(group);
        userDao.addUserGroup(baseUser,group);
        EasyMock.expectLastCall();
        //验证了取消的关联，删除这些关联
        userDao.deleteUserRole(baseUser.getId(),3);
        EasyMock.expectLastCall();
        userDao.deleteUserGroup(baseUser.getId(),3);
        EasyMock.expectLastCall();
        EasyMock.replay(userDao,roleDao,groupDao);
        userService.update(baseUser,rids,gids);
        EasyMock.verify(userDao,roleDao,groupDao);
    }
}
