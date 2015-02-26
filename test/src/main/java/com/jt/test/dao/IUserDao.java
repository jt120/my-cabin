package com.jt.test.dao;

import com.jt.test.model.User;

/**
 * @author ze.liu
 * @since 2014/5/28
 */
public interface IUserDao {

    void add(User user);
    void delete(String username);
    User load(String username);
}
