package com.rong.cms.util;

import com.rong.cms.model.RoleType;
import org.junit.Assert;
import org.junit.Test;

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
}
