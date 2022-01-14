package com.lwr.crm.workbench.dao;

import com.lwr.crm.workbench.domain.Activity;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林文润
 * @version 1.0
 */
public interface ActivityDao {
    int save(Activity activity);

    int getTotalByCondition(HashMap<String, Object> map);

    List<Activity> getActivityListByCondition(HashMap<String, Object> map);
}
