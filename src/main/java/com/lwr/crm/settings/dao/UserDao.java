package com.lwr.crm.settings.dao;

import com.lwr.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author 林文润
 * @version 1.0
 */
public interface UserDao {
    User login(Map<String,String> map);

    List<User> getUserList();
}
