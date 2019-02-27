package com.rong.cms.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

//FastJson是阿里的开源框架,高效
public class JsonFastJsonUtil {
    private static JsonFastJsonUtil jsonFastJsonUtil;

    private JsonFastJsonUtil() {
    }

    public static JsonFastJsonUtil getInstance() {
        if (jsonFastJsonUtil == null) jsonFastJsonUtil = new JsonFastJsonUtil();
        //全局修改日期格式
        JSON.DEFFAULT_DATE_FORMAT = "yyyy.MM.dd HH:mm";
        return jsonFastJsonUtil;
    }

    //Java 对象转换换为 JSON 对象
    public String obj2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    //JSON 转换成对象
    //使用 JSONObject（fastJson提供的json对象） 和 JSONArray（fastJson提供json数组对象） 对象
    public <T> T json2Obj(String json, Class<T> clz) {
        return JSON.parseObject(json, clz);
    }

    //通过list数组转换程json
    public String list2Json(List<Object> lists) {
        return JSONArray.toJSONString(lists);
    }

    //json转换为list
    public <T> List<T> json2List(String json, Class<T> clz) {
        return JSONArray.parseArray(json, clz);
    }
}
