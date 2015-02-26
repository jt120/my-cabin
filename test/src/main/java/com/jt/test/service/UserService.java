package com.jt.test.service;

import com.jt.test.dao.IUserDao;
import com.jt.test.model.User;
import com.jt.test.model.UserException;

/**
 * @author ze.liu
 * @since 2014/5/28
 */
public class UserService implements IUserService {

    private IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void delete(String username) {
        userDao.delete(username);
    }

    @Override
    public User load(String username) {
        return userDao.load(username);
    }

    @Override
    public User login(User user) {
        User mu = load(user.getUsername());
        if(mu==null) throw new UserException("用户不存在");
        if(!mu.getPassword().equals(user.getPassword())) throw new UserException("密码不正确");
        return mu;
    }
}
