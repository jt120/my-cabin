package config;

import com.jt.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * since 2015/2/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StartOne.class)
public class AppMain {

    @Autowired
    private UserService userService;

    @Test
    public void testFirst() throws Exception {
        System.out.println(userService);
        Assert.assertNotNull(userService);
    }
}
