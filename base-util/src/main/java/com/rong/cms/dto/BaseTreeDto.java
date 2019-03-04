package com.rong.cms.dto;

import com.rong.cms.model.ITree;

import java.util.List;

public class BaseTreeDto implements ITree {

    private Integer id;
    private String name;
    private Integer pid;
    private String path;
    private List<? extends ITree> children;

    public BaseTreeDto() {
    }

    public BaseTreeDto(Integer id, String name, Integer pid, String path, List<? extends ITree> children) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.path = path;
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<? extends ITree> getChildren() {
        return children;
    }

    public void setChildren(List<? extends ITree> children) {
        this.children = children;
    }
}
