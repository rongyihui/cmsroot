package com.rong.cms.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumUtils {
    /**
     * 将枚举类转换为0123....列表
     * @param clz 枚举类
     * @return ordinal的list数组
     */
    public static List<Integer> enum2ordinal(Class<? extends Enum> clz){
        if (!clz.isEnum()) return null;
        Enum[] enums =  clz.getEnumConstants();
        List<Integer> integers = new ArrayList<>();
        for (Enum en:enums){
            integers.add(en.ordinal());
        }
        return integers;
    }

    /**
     * 将枚举类转换为String列表
     * @param clz 枚举类
     * @return String的list数组
     */
    public static List<String> enum2String(Class<? extends Enum> clz){
        if (!clz.isEnum()) return null;
        Enum[] enums =  clz.getEnumConstants();
        List<String> strs = new ArrayList<>();
        for (Enum en:enums){
            strs.add(en.name());
        }
        return strs;
    }

    /**
     * 将枚举类转换为 序号、String 列表
     * @param clz clz 枚举类
     * @return ordinal和String的map数组
     */
    public static Map<Integer,String> enum2Map(Class<? extends Enum> clz){
        if (!clz.isEnum()) return null;
        Enum[] enums =  clz.getEnumConstants();
        Map<Integer,String> maps = new HashMap<>();
        for (Enum en:enums){
            maps.put(en.ordinal(),en.name());
        }
        return maps;
    }

    /**
     * 通过枚举的类名称获取String列表
     * @param clz clz 枚举类
     * @param name 某个属性值 例如name
     * @return String的list数组
     */
    public static List<String> enumName2List(Class<? extends Enum> clz,String name){
        if (!clz.isEnum()) return null;
        try {
            Enum[] enums =  clz.getEnumConstants();
            List<String> strs = new ArrayList<>();
            for (Enum en:enums){
                strs.add((String)PropertyUtils.getProperty(en,name));
            }
            return strs;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过枚举的类名将枚举类转换为 String、String 列表
     * @param clz clz 枚举类
     * @param name 某个属性值 例如name
     * @return String的Map数组
     */
    public static Map<Integer,String> enumName2Map(Class<? extends Enum> clz,String name){
        if (!clz.isEnum()) return null;
        try {
            Enum[] enums =  clz.getEnumConstants();
            Map<Integer,String> maps = new HashMap<>();
            for (Enum en:enums){
                maps.put(en.ordinal(),(String)PropertyUtils.getProperty(en,name));
            }
            return maps;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过枚举的类名将枚举类转换为 String、String 列表
     * @param clz clz 枚举类
     * @param name 某个属性值 例如name
     * @return String的Map数组
     */
    public static Map<String,String> enumName2StringMap(Class<? extends Enum> clz,String name){
        if (!clz.isEnum()) return null;
        try {
            Enum[] enums =  clz.getEnumConstants();
            Map<String,String> maps = new HashMap<>();
            for (Enum en:enums){
                maps.put(en.name(),(String)PropertyUtils.getProperty(en,name));
            }
            return maps;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
