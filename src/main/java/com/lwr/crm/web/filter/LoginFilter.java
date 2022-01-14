package com.lwr.crm.web.filter;

import com.lwr.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 林文润
 * @version 1.0
 */
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入到判断是否登录的过滤器");
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String path=request.getServletPath();
        System.out.println(path);
        if("/login.jsp".equals(path)||"settings/user/login.do".equals(path)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            if (session != null) {//表示登录过
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                //重定向 response.sendRedirect(/项目名/资源名);
            /*
            为什么使用重定向，请求转发不行吗？
            转发之后，路径会停留在老路径上，而不是跳转后的最新资源
            我们应该在为用户跳转到登录界面后，将浏览器的路径自动设置为最新的路径
             */
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                //servletRequest.getRequestDispatcher().forward();请求转发
            }
        }
    }
}
