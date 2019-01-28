package com.rong.cms.dao;


import com.rong.cms.exception.CmsException;
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
    private UserDao userDao;
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
                , "5418@qq.com", "12345678901", "0"
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
}
