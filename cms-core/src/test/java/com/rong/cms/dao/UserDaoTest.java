package com.rong.cms.dao;


import com.rong.cms.model.*;
import org.junit.Assert;
import org.junit.BeforeClass;
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
public class UserDaoTest extends BaseDaoTest {

    @Resource(name = "userDao")
    private IUserDao userDao;
    @Resource
    private IRoleDao roleDao;
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

    @Test
    public void add() {
        User user = new User("admin", "123", "管理员"
                , "5418@qq.com", "12345678901", 0
                , new Date(), new Date());
        userDao.add(user);
        Assert.assertEquals("用户添加失败", user.getNickname(), userDao.load(user.getId()).getNickname());
    }

    @Test
    public void listUserRoles() {
        List<Role> roles = userDao.listUserRoles(2);
        Assert.assertEquals("list指定用户角色列表查询失败", 3, roles.size());
    }

    @Test
    public void listUserGroups() {
        List<Group> groups = userDao.listUserGroups(2);
        Assert.assertEquals("list指定用户group列表查询失败", 2, groups.size());
    }

    @Test
    public void loadUserRole() {
        UserRole userRole = userDao.loadUserRole(1, 1);
        Assert.assertEquals("用户、角色关联表查询失败", 1, userRole.getId());
    }
    @Test
    public void loadUserGroup(){
        UserGroup userGroup = userDao.loadUserGroup(1, 1);
        Assert.assertEquals("用户、组关联表查询失败", 1, userGroup.getId());
    }
    @Test
    public void loadByUsername() {
        User user =userDao.loadByUsername("admin2");
        Assert.assertEquals("用户通过用户名查询失败", 2, user.getId());
    }
    @Test
    public void listRoleUsers(){
        List<User> users = userDao.listRoleUsers(2);
        List<User> usersExpect = Arrays.asList(userDao.load(2),userDao.load(3));
        Assert.assertEquals("角色获取用户数量错误",2,users.size());
        for(int i =0;i<2;i++){
            Assert.assertEquals("用户不正确",usersExpect.get(i).getNickname(),users.get(i).getNickname());
        }
    }

    @Test
    public void listRoleUsers1(){
        List<User> users = userDao.listRoleUsers(RoleType.ROLE_ADMIN);
        List<User> usersExpect = Arrays.asList(userDao.load(1),userDao.load(2));
        Assert.assertEquals("角色权限获取用户数量错误",2,users.size());
        for(int i =0;i<2;i++){
            Assert.assertEquals("用户不正确",usersExpect.get(i).getNickname(),users.get(i).getNickname());
        }
    }
    @Test
    public void listGroupUsers() {
        List<User> users = userDao.listGroupUsers(1);
        List<User> usersExpect = Arrays.asList(userDao.load(1),userDao.load(3));
        Assert.assertEquals("角色权限获取用户数量错误",2,users.size());
        for(int i =0;i<2;i++){
            Assert.assertEquals("用户不正确",usersExpect.get(i).getNickname(),users.get(i).getNickname());
        }
    }
    @Test
    public void addUserRole(){
        Role role = roleDao.load(1);
        User user = userDao.load(3);
        userDao.addUserRole(user,role);
        UserRole userRole = userDao.loadUserRole(user.getId(),role.getId());
        Assert.assertNotNull("用户、角色添加关联失败",userRole);
        Assert.assertEquals("关联对象错误",6,userRole.getId());
    }
    @Test
    public void addUserGroup(){
        Group group = groupDao.load(1);
        User user = userDao.load(2);
        userDao.addUserGroup(user,group);
        UserGroup userGroup = userDao.loadUserGroup(user.getId(),group.getId());
        Assert.assertNotNull("用户、组添加关联失败",userGroup);
        Assert.assertEquals("关联对象错误",6,userGroup.getId());
    }
    @Test
    public void deleteUserRole(){
        int uid= 1;
        userDao.deleteUserRole(uid);
        List<Role> userRoles = userDao.listUserRoles(uid);
        Assert.assertTrue("删除用户所有角色失败",userRoles.size()<=0);
    }
    @Test
    public void deleteUserGroup(){
        int gid= 1;
        userDao.deleteUserGroup(gid);
        List<Group> userGroups = userDao.listUserGroups(gid);
        Assert.assertTrue("删除用户所有组失败",userGroups.size()<=0);
    }
    @Test
    public void findUser(){
        Pager users = userDao.findUser();
        Assert.assertEquals("用户pager失败",3,users.getData().size());
    }
    @Test
    public void deleteUserRoles(){
        int uid= 2;
        int rid= 2;
        userDao.deleteUserRole(uid,rid);
        UserRole userRole = userDao.loadUserRole(uid,rid);
        Assert.assertNull("删除失败",userRole);
    }
    @Test
    public void deleteUserGroups(){
        int uid= 2;
        int gid= 2;
        userDao.deleteUserGroup(uid,gid);
        UserGroup userGroup = userDao.loadUserGroup(uid,gid);
        Assert.assertNull("删除失败",userGroup);
    }
    @Test
    public void listUserRolesId(){
        int uid = 2;
        List<Integer> r =  userDao.listUserRolesId(uid);
        Integer[] rids = r.toArray(new Integer[r.size()]);
        Assert.assertEquals("通过用户获取角色id失败",3,rids.length);
    }

    @Test
    public void listUserGroupsId(){
        int uid = 2;
        List<Integer> g =  userDao.listUserGroupsId(uid);
        Integer[] gids = g.toArray(new Integer[g.size()]);
        Assert.assertEquals("通过用户获取角色id失败",2,gids.length);
    }
}
