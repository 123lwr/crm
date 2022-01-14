package com.lwr.crm.settings.service;

import com.lwr.crm.exception.LoginException;
import com.lwr.crm.settings.domain.User;

import java.util.List;

/**
 * @author 林文润
 * @version 1.0
 */
public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
