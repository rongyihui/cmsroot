package com.rong.cms.model;

import javax.persistence.*;

@Entity
@Table(name="t_department")
public class Department {
    private int id;
    private String depName;
    private String level;
    /**
     * 0表示正常 -1表示失效
     */
    private int status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Department(){}
    public Department(String depName, String level, int status) {
        this.depName = depName;
        this.level = level;
        this.status = status;
    }
}
