package com.rong.cms.util;

import com.thoughtworks.xstream.XStream;

import java.util.Map;
import java.util.Set;

/**
 * xml和对象的相互转换工具
 */
public class XstreamUtil {
    private static XstreamUtil xstreamUtil;

    private XstreamUtil() {
    }

    public static XstreamUtil getInstance() {
        if (xstreamUtil == null) xstreamUtil = new XstreamUtil();
        return xstreamUtil;
    }

    /**
     * 通过对象转换为xml
     *
     * @param obj
     * @param alias
     * @return
     */
    public String obj2Xml(Object obj, Map<String, Class<?>> alias) {
        XStream stream = setAlias(alias);
        return stream.toXML(obj);
    }
    /**
     * 通过xml转换为对象
     *
     * @param xml
     * @param alias
     * @return
     */
    public Object xml2Obj(String xml, Map<String, Class<?>> alias) {
        XStream stream = setAlias(alias);
        return stream.fromXML(xml);

    }

    /**
     * 去包名
     * @param alias
     * @return
     */
    private XStream setAlias(Map<String, Class<?>> alias) {
        XStream stream = new XStream();
        Set<String> keys = alias.keySet();
        for (String key : keys) {
            stream.alias(key, alias.get(key));
        }
        return stream;
    }
}
