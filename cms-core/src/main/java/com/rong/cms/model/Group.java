package com.rong.cms.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 通过组来
 */
@Entity
@Table(name = "t_group")
public class Group {
    private int id;
    private String name;
    /**
     * 组描述
     */
    private String intro;
    private Date createDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @NotNull(message = "组名称不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull(message = "描述不能为空")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
