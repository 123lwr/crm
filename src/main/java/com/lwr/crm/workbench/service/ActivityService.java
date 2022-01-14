package com.lwr.crm.workbench.service;

import com.lwr.crm.vo.PaginationVo;
import com.lwr.crm.workbench.domain.Activity;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林文润
 * @version 1.0
 */
public interface ActivityService {
    boolean save(Activity activity);

    PaginationVo<Activity> pageList(HashMap<String, Object> map);

    boolean delete(String[] ids);
}
