package com.lwr.crm.settings.service.impl;

import com.lwr.crm.exception.LoginException;
import com.lwr.crm.settings.dao.UserDao;
import com.lwr.crm.settings.domain.User;
import com.lwr.crm.settings.service.UserService;
import com.lwr.crm.utils.DateTimeUtil;
import com.lwr.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 林文润
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String,String> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user=userDao.login(map);
        if(user==null){//表示返回的user对象为空，则说明账号密码不存在
            throw new LoginException("账号密码错误");
        }
        //如果程序能够执行到该行，表示账号密码是正确的
        String expireTime=user.getExpireTime();
        String currentTime= DateTimeUtil.getSysTime();
        if(expireTime.compareTo(currentTime)<0){//表示有效时间小于当前时间
            throw new LoginException("账号已失效");
        }
        //判断锁定时间
        String lockState=user.getLockState();
        if("0"==lockState){
            throw new LoginException("账号已锁定");
        }
        //判断ip地址
        //String allowIps=user.getAllowIps();
        //if(!allowIps.contains(ip)){
            //throw new RuntimeException("ip地址受限");
        //}
        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> uList=userDao.getUserList();
        return uList;
    }
}
