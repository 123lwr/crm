package com.lwr.crm.settings.web.controller;

import com.lwr.crm.settings.domain.User;
import com.lwr.crm.settings.service.UserService;
import com.lwr.crm.settings.service.impl.UserServiceImpl;
import com.lwr.crm.utils.MD5Util;
import com.lwr.crm.utils.PrintJson;
import com.lwr.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 林文润
 * @version 1.0
 */
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到用户控制器");
        String path=request.getServletPath();
        if("/settings/user/login.do".equals(path)){
            //实现方法
            login(request,response);
        }else {

        }
    }

    private void login(HttpServletRequest request,HttpServletResponse response){
        System.out.println("进入到验证登录界面");
        String loginAct=request.getParameter("loginAct");
        String loginPwd=request.getParameter("loginPwd");
        //将密码的明文形势转换为md5的密文形式
        loginPwd= MD5Util.getMD5(loginPwd);
        //接受ip地址
        String ip=request.getRemoteAddr();
        System.out.println("ip======"+ip);
        //未来的业务层开发，统一使用代理类的接口对象
        UserService userService=(UserService) ServiceFactory.getService(new UserServiceImpl());
        try {
            User user = userService.login(loginAct, loginPwd, ip);
            request.getSession().setAttribute("user", user);
            //程序运行到这里表示没有异常抛出，登录成功
            //String str="{\"success\":true}";
            //response.getWriter().print(str);
            PrintJson.printJsonFlag(response,true);
        }catch (Exception e){
            e.printStackTrace();
            //执行到这里表示登录失败
            String msg=e.getMessage();
            /*我们现在是controller，需要为ajax请求提供多项信息

            可以有两种手段来处理
            第一种为：将多项信息打包为map，将map解析为json串
            第二组为:创建一个vo类
                private boolean success;
                private String msg;

                如果在之后的项目中还大量使用
             */
            Map<String, Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}
