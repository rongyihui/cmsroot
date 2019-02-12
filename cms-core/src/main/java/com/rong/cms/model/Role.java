package com.rong.cms.model;

import javax.persistence.*;


/**
 * 角色对象，对应可以访问的功能
 *
 */
@Entity
@Table(name = "t_role")
public class Role {
    private int id;
    private String name;
    /**
     * 角色编号，枚举类型
     */
    private RoleType roleType;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Enumerated(EnumType.ORDINAL)
    @Column(name="role_type")
    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
