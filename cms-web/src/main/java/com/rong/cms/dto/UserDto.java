package com.rong.cms.dto;

import com.rong.cms.model.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserDto {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    /**
     * 0表示管理员，1表示正常用户，-1表示黑名单
     */
    private int status;
    private Date bornDate;
    private Date createDate;
    private Integer[] roleIds;
    private Integer[] groupIds;

    public User getUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setStatus(status);
        user.setBornDate(bornDate);
        user.setCreateDate(createDate);
        return user;
    }

    public UserDto(){}
    public UserDto(User user,Integer[] roleIds, Integer[] groupIds) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setNickname(user.getNickname());
        this.setEmail(user.getEmail());
        this.setPhone(user.getPhone());
        this.setStatus(user.getStatus());
        this.setBornDate(user.getBornDate());
        this.setCreateDate(user.getCreateDate());
        this.setRoleIds(roleIds);
        this.setGroupIds(groupIds);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @NotEmpty(message = "用户名不能为空")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotEmpty(message = "用户密码不能为空")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotEmpty(message = "用户名称不能为空")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    @Email(message = "邮件格式不正确")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Length(min = 11,max = 11,message = "电话长度为11位")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }

    public Integer[] getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Integer[] groupIds) {
        this.groupIds = groupIds;
    }
}
