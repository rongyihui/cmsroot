package com.rong.cms.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumUtils {
    /**
     * 将枚举类转换为0123....
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
     * 将枚举类转换为String
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
     * 将枚举类转换为String
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
}
