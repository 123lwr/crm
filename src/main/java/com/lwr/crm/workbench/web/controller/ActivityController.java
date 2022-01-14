package com.lwr.crm.workbench.web.controller;

import com.lwr.crm.settings.domain.User;
import com.lwr.crm.settings.service.UserService;
import com.lwr.crm.settings.service.impl.UserServiceImpl;
import com.lwr.crm.utils.*;
import com.lwr.crm.vo.PaginationVo;
import com.lwr.crm.workbench.domain.Activity;
import com.lwr.crm.workbench.service.ActivityService;
import com.lwr.crm.workbench.service.impl.ActivityServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 林文润
 * @version 1.0
 */
public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");
        String path=request.getServletPath();
        System.out.println(path);
        if("/workbench/activity/getUserList.do".equals(path)){
            //实现方法
            getUserList(request,response);
        }else if("/workbench/activity/save.do".equals(path)){
            save(request,response);
        }else if("/workbench/activity/pageList.do".equals(path)){
            pageList(request,response);
        }else if("/workbench/activity/delete.do".equals(path)){
            delete(request,response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动的删除操作");
        String ids[]=request.getParameterValues("id");
        ActivityService as=(ActivityService)ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag=as.delete(ids);
        PrintJson.printJsonFlag(response,flag);
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到查询市场信息列表的操作(结合条件查询和分页查询)");
        String name=request.getParameter("name");
        String owner=request.getParameter("owner");
        String startDate=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
        String pageNoStr=request.getParameter("pageNo");
        int pageNo=Integer.valueOf(pageNoStr);
        String pageSizeStr=request.getParameter("pageSize");
        int pageSize=Integer.valueOf(pageSizeStr);
        //计算略过的记录数
        int skipCount=(pageNo-1)*pageSize;
        //不能用vo，vo是传输据给前端,所以使用map
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        ActivityService as=(ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        /*
        想要知道需要从持久层拿什么，就要知道前端界面需要什么
        前端要：
        市场活动信息列表
        查询的总条数
         */
        PaginationVo<Activity> vo=as.pageList(map);
        PrintJson.printJsonObj(response,vo);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动的添加操作");
        String id= UUIDUtil.getUUID();
        String owner=request.getParameter("owner");
        String name=request.getParameter("name");
        String startDate=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
        String cost=request.getParameter("cost");
        String description=request.getParameter("description");
        //创建人、创建时间应该是自动生成
        String createTime= DateTimeUtil.getSysTime();
        //创建人为当前登录的用户
        String createBy=((User)(request.getSession().getAttribute("user"))).getCreateBy();
        ActivityService as= (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Activity activity=new Activity();
        activity.setId(id);
        activity.setOwner(owner);
        activity.setName(name);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setCost(cost);
        activity.setDescription(description);
        boolean flag=as.save(activity);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得用户信息列表");
        UserService userService=(UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> uList=userService.getUserList();
        PrintJson.printJsonObj(response,uList);
    }
}
