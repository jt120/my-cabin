package com.jt.test.service;

import com.jt.test.model.User;

/**
 * @author ze.liu
 * @since 2014/5/28
 */
public interface IUserService {

    void add(User user);
    void delete(String username);
    User load(String username);
    User login(User user);
}
