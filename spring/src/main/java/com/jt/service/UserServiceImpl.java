package com.jt.service;

import com.jt.bean.User;
import com.jt.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public long add(User user) {
        return userDao.add(user);
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }
}
