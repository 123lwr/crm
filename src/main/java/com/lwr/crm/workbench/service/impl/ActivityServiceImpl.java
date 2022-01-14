package com.lwr.crm.workbench.service.impl;

import com.lwr.crm.utils.SqlSessionUtil;
import com.lwr.crm.vo.PaginationVo;
import com.lwr.crm.workbench.dao.ActivityDao;
import com.lwr.crm.workbench.domain.Activity;
import com.lwr.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林文润
 * @version 1.0
 */
public class ActivityServiceImpl implements ActivityService {
    //使用SqlSessionUtil包下面的方法得到SqlSession对象，通过getMapper动态创建实现类
    private ActivityDao activityDao= SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    @Override
    public boolean save(Activity activity) {
        boolean flag=true;
        int count=activityDao.save(activity);
        if(count!=1){
            flag=false;
        }
        System.out.println("变量flag为："+flag);
        return flag;
    }

    @Override
    public PaginationVo<Activity> pageList(HashMap<String, Object> map) {
        //取得total
        int total=activityDao.getTotalByCondition(map);
        //取得dataList
        List<Activity> dataList= activityDao.getActivityListByCondition(map);
        //将total和dataList封装到vo中
        PaginationVo<Activity> vo=new PaginationVo<>();
        vo.setTotal(total);
        vo.setDataList(dataList);
        //将vo返回
        return vo;
    }

    @Override
    public boolean delete(String[] ids) {

        return false;
    }
}
