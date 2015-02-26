package com.jt.dao;

import com.jt.bean.User;

/**
 * since 2015/2/26.
 */
public interface UserDao {

    long add(User user);

    User findByName(String name);
}
