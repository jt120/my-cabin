package com.jt.test.service;

import com.jt.test.dao.IUserDao;
import com.jt.test.dao.UserDaoStub;
import com.jt.test.model.User;
import com.jt.test.model.UserException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author ze.liu
 * @since 2014/5/28
 */
public class UserServiceTest {

    private IUserService userService;
    private User user;

    @Before
    public void setUp() {
        IUserDao userDao = new UserDaoStub();
        userService = new UserService(userDao);
        user = new User(1, "admin", "123", "管理员");
    }


    @Test
    public void testAdd() {
        userService.add(user);
        User u = userService.load(user.getUsername());
        assertEquals(user, u);
    }


    @Test
    public void testDelete() {
        userService.add(user);
        userService.delete("admin");
        User u = userService.load("admin");
        assertNull(u);
    }

    @Test
    public void testLogin() {
        userService.add(user);
        User un = userService.login(user);
        assertEquals(user, un);
    }

    @Test(expected = UserException.class)
    public void testLoginUsernameError() {
        userService.add(user);
        User u = new User(1, "admin1", "123", "管理员");
        User un = userService.login(u);
    }

    @Test(expected = UserException.class)
    public void testLoginPasswordError() {
        userService.add(user);
        User u = new User(1, "admin", "1234", "管理员");
        User un = userService.login(u);
    }
}
