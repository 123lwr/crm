package com.lwr.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 林文润
 * @version 1.0
 */
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入到过滤字符编码的过滤器");
        //过滤post请求中文参数乱码
        servletRequest.setCharacterEncoding("UTF-8");//设置请求的编码为utf-8
        //过滤响应流响应中文乱码
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
