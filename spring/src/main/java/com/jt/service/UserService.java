package com.jt.service;

import com.jt.bean.User;

/**
 * since 2015/2/25.
 */
public interface UserService {

    long add(User user);

    User findByName(String name);
}
