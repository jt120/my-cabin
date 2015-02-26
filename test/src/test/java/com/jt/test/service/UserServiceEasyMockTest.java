package com.jt.test.service;

import com.jt.test.dao.IUserDao;
import com.jt.test.dao.UserDaoStub;
import com.jt.test.model.User;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author ze.liu
 * @since 2014/5/29
 */
public class UserServiceEasyMockTest {

    @Test
    public void test01() {
        IUserDao userDao = EasyMock.createMock(IUserDao.class);

        User u = new User(1,"admin","123","admin");
        EasyMock.expect(userDao.load("admin")).andReturn(u);
        EasyMock.replay(userDao);
        IUserService userService = new UserService(userDao);
        User nu = userService.load("admin");
        assertEquals(u,nu);
        EasyMock.verify(userDao);

    }

    @Test
    public void test02() {
        IUserDao userDao = EasyMock.createMock(UserDaoStub.class);
        User u = new User(1, "admin", "123", "admin");
        EasyMock.expect(userDao.load("admin")).andReturn(u);
        EasyMock.replay(userDao);
        IUserService userService = new UserService(userDao);
        User nu = userService.load("admin");
        assertEquals(u, nu);
        EasyMock.verify(userDao);
    }

    @Test
    public void test03() {
        IUserDao userDao = EasyMock.createMock(UserDaoStub.class);
        User u = new User(1, "admin", "123", "admin");
        EasyMock.expect(userDao.load("admin")).andReturn(u);
        userDao.delete("admin");
        EasyMock.expectLastCall();
        EasyMock.expect(userDao.load("bbb")).andReturn(u);
        EasyMock.replay(userDao);
        IUserService userService = new UserService(userDao);
        User nu = userService.load("admin");
        userService.delete("admin");
        userService.load("bbb");
        assertEquals(u, nu);
        EasyMock.verify(userDao);
    }

    @Test
    public void test04() {
        String[] s = {"hello","java"};
        System.out.println(Arrays.toString(s));
    }
}
