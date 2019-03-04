package com.rong.cms.util;

import com.rong.cms.dto.BaseTreeDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeUtilTest {
    private List<BaseTreeDto> treeList;
    public TreeUtilTest(){
        treeList = new ArrayList<>();
        treeList.add(new BaseTreeDto(1,"系统管理",null));
        treeList.add(new BaseTreeDto(2,"人员管理",null));
        treeList.add(new BaseTreeDto(3,"信息管理",null));
        treeList.add(new BaseTreeDto(4,"地址管理",null));
        treeList.add(new BaseTreeDto(5,"角色管理",1));
        treeList.add(new BaseTreeDto(6,"用户组管理",1));
    }

    @Test
    public void tree2List(){
        List list = new TreeUtil().tree2List(treeList);
        String json = JsonFastJsonUtil.getInstance().list2Json(list);
        System.out.println(json);
    }
}
