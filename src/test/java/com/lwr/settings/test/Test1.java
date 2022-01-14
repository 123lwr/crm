package com.lwr.settings.test;

import com.lwr.crm.utils.DateTimeUtil;
import com.lwr.crm.utils.MD5Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 林文润
 * @version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        String expireTime="2019-10-10 10:10:10";
        //Date date = new Date();
        //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String str = sdf.format(date);
        //System.out.println(str);
        //String sysTime = DateTimeUtil.getSysTime();
        //int count = expireTime.compareTo(sysTime);
        //System.out.println(count);
        String pwd="123";
        String md5 = MD5Util.getMD5(pwd);
        System.out.println(md5);
    }
}
