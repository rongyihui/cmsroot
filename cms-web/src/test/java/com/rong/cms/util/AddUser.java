package com.rong.cms.util;

import com.rong.cms.model.User;
import com.rong.cms.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class AddUser {
    int hightPos, lowPos;
    IUserService userService;

    @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Test
    public void addUser (){
        User u =null;
        Random random = null;
        Date date =null;
        Integer[] in = new Integer[]{1};
        for(int i=100;i<300;i++){
            random = new Random();
            date = new Date();
            u = new User("user"+i,"123",getNickName(random.nextInt(2)+2)
                    ,"54188"+random.nextInt(500)+"@qq.com"
                    ,"135487"+random.nextInt(6000),1,date,date);
            userService.add(u,in,in);
        }
    }

    private String getNickName(int len){
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }
}
