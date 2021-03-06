package com.rong.cms.util;

import com.rong.cms.dto.BaseTreeDto;

import java.util.ArrayList;
import java.util.List;

public  class TreeUtil<T extends BaseTreeDto> {

    private List<T> trees = new ArrayList<>();

    /**
     * 将list转换为json
     * @param treeList list
     * @return 符合layui的list数组
     */
    public List<T> tree2List(List<T> treeList){
        trees = treeList;
        List<T> treeNodes = new ArrayList<>();
        List<T> rootNodes = getRootNodes();
        for (T rootNode : rootNodes) {
            forChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**
     * 递归子节点
     * @param pt
     */
    public void forChildNodes(T pt) {
        List<T> children = getChildNodes(pt);
        if (!children.isEmpty()) {
            for (T child : children) {
                forChildNodes(child);
            }
            pt.setChildren(children);
        }
    }

    /**
     * 获取某个父节点所有的子节点
     * @param pt
     * @return
     */
    public List<T> getChildNodes(T pt) {
        List<T> childNodes = new ArrayList<>();
        for (T n : trees) {
            if (pt.getId().equals(n.getPid())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    /**
     * 获取所有的根节点,默认根节点pid为0
      * @return
     */
    public List<T> getRootNodes(){
        List<T> rootNodes = new ArrayList<>();
        for (T n : trees) {
            if (n.getPid()==null) {
                n.setPid(0);
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }
}
