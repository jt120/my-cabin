package config;

import com.jt.service.UserService;
import com.jt.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * since 2015/2/25.
 */
@Configuration
public class StartOne {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}
