package com.rong.cms.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

//一个maven依赖jackson就够了
public class JsonJacksonUtil {
    private static JsonJacksonUtil jsonJacksonUtil;
    private static JsonFactory jsonFactory;
    private static ObjectMapper objectMapper;
    private JsonJacksonUtil(){}

    public static JsonJacksonUtil getInstance() {
        if(jsonJacksonUtil ==null) jsonJacksonUtil =new JsonJacksonUtil();
        return jsonJacksonUtil;
    }

    public static  ObjectMapper getObjectMapper() {
        if(objectMapper==null) {
            objectMapper=new ObjectMapper();
            //Include.Include.ALWAYS 默认
            //Include.NON_DEFAULT 属性为默认值不序列化
            //Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
            //Include.NON_NULL 属性为NULL 不序列化
            //只对Object有用，Map List不起作用
            objectMapper.setSerializationInclusion(JsonInclude.Include  .NON_NULL);
            //忽略对象没有的属性，避免报JsonMappingException异常
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            //解析器支持解析单引号
            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            //日期转换格式
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy.MM.dd HH:mm"));
        }
        return objectMapper;
    }

    public static JsonFactory getJsonFactory(){
        if(jsonFactory==null)  jsonFactory=new JsonFactory();
        return jsonFactory;
    }

    /**
     * 将对象转换为json
     * @param obj
     * @return
     */
    public String obj2Json(Object obj){
        try {
            getObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json转换为对象
     * @param json
     * @param clz
     * @return
     */
    public Object json2Obj(String json,Class<?> clz) {
        try {
            getObjectMapper();
            return objectMapper.readValue(json,clz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
