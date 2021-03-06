package com.rong.cms.util;

import com.rong.cms.model.Channel;
import com.rong.cms.model.ChannelType;
import com.rong.cms.model.RoleType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnumUtilTest {

    @Test
    public void enum2ordinal(){
        List<Integer> ints = EnumUtils.enum2ordinal(RoleType.class);
        for (int i=0;i<ints.size();i++){
            Assert.assertEquals("转换ordinal错误", Integer.valueOf(i),ints.get(i));
        }
    }

    @Test
    public void enum2String(){
        List<String> strings = EnumUtils.enum2String(RoleType.class);
        Assert.assertEquals("转换String错误",RoleType.ROLE_ADMIN.name(),strings.get(0));
        Assert.assertEquals("转换String错误",RoleType.ROLE_PUBLIC.name(),strings.get(1));
        Assert.assertEquals("转换String错误",RoleType.ROLE_BLACK.name(),strings.get(2));
    }

    @Test
    public void enum2Map(){
        Map<Integer,String> maps = EnumUtils.enum2Map(RoleType.class);
        Assert.assertEquals("转换String错误",RoleType.ROLE_ADMIN.name(),maps.get(0));
        Assert.assertEquals("转换String错误",RoleType.ROLE_PUBLIC.name(),maps.get(1));
        Assert.assertEquals("转换String错误",RoleType.ROLE_BLACK.name(),maps.get(2));
    }

    @Test
    public void enumName2List(){
        List<String> strings = EnumUtils.enumName2List(ChannelType.class,"name");
        Assert.assertEquals("通过属性名称转换list错误",ChannelType.NAV_CHANNEL.getName(),strings.get(0));
        Assert.assertEquals("通过属性名称转换list错误",ChannelType.TOP_LIST.getName(),strings.get(1));
        Assert.assertEquals("通过属性名称转换list错误",ChannelType.TOP_CONTENT.getName(),strings.get(2));
        Assert.assertEquals("通过属性名称转换list错误",ChannelType.TOP_IMG.getName(),strings.get(3));
    }
    @Test
    public void enumName2Map(){
        Map<Integer,String> maps = EnumUtils.enumName2Map(ChannelType.class,"name");
        Assert.assertEquals("通过属性名称转换map错误",ChannelType.NAV_CHANNEL.getName(),maps.get(0));
        Assert.assertEquals("通过属性名称转换map错误",ChannelType.TOP_LIST.getName(),maps.get(1));
        Assert.assertEquals("通过属性名称转换map错误",ChannelType.TOP_CONTENT.getName(),maps.get(2));
        Assert.assertEquals("通过属性名称转换map错误",ChannelType.TOP_IMG.getName(),maps.get(3));
    }

    @Test
    public void enumName2StringMap(){
        Map<String,String> maps = EnumUtils.enumName2StringMap(ChannelType.class,"name");
        Assert.assertEquals("通过属性名称转换map错误",ChannelType.NAV_CHANNEL.getName(),maps.get(ChannelType.NAV_CHANNEL.name()));
        Assert.assertEquals("通过属性名称转换map错误",ChannelType.TOP_LIST.getName(),maps.get(ChannelType.TOP_LIST.name()));
        Assert.assertEquals("通过属性名称转换map错误",ChannelType.TOP_CONTENT.getName(),maps.get(ChannelType.TOP_CONTENT.name()));
        Assert.assertEquals("通过属性名称转换map错误",ChannelType.TOP_IMG.getName(),maps.get(ChannelType.TOP_IMG.name()));
    }

}
