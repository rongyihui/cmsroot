package com.rong.cms.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class XpathUtil {
    private static XpathUtil xpathUtil;
    private SAXReader saxReader;
    private Document doc;
    private XpathUtil(){}
    public static XpathUtil getInstance(){
        if(xpathUtil==null) xpathUtil = new XpathUtil();
        return xpathUtil;
    }

    /**
     * 通过xml文件读取,
     *
     * 相对路径查找
     *          例如 user
     * 通过绝对路径查找，
     *          例如/users/user[age='16']
     *             /users/user[@id>=1]
     *             /users/user[contains(name,'zhang')]/money
     *             /users/user[contains(name,'zhang') and age>20]/money
     *
     * List<Node>   获取元素方法
     * for(Node no:lists) {
     *  System.out.println(((Element)no).elementText(elementText));
     * }
     *
     * @param file 文件
     * @param xpath 节点名称
     * @return
     */
    public List<Node> getElementText(File file, String xpath){
        try {
            doc = saxReader.read(file);
            //1.相对路径查找，从当前节点查找子节点
            return doc.getRootElement().selectNodes(xpath);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

}
