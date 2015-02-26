package com.jt.test.dao;

import com.jt.test.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ze.liu
 * @since 2014/5/28
 */
public class UserDaoStub implements IUserDao {

    private Map<String, User> map = new HashMap<String, User>();
    @Override
    public void add(User user) {
        map.put(user.getUsername(),user);
    }

    @Override
    public void delete(String username) {
        map.remove(username);
    }

    @Override
    public User load(String username) {
        return map.get(username);
    }
}
