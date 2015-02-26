package ne;

import model.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ze.liu
 * @since 2014/5/23
 */
public class UserJson {

    @Test
    public void test01() {
        ObjectMapper objectMapper = new ObjectMapper();
        User u = new User("zhangsan","123");
        try {
            String s = objectMapper.writeValueAsString(u);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        ObjectMapper objectMapper = new ObjectMapper();
        User u = new User("zhangsan", "123");
        Map<String,String> map = new HashMap<String,String>();
        map.put("street","天坛");
        map.put("city","beijing");
        u.setInfo(map);
        try {
            String s = objectMapper.writeValueAsString(u);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
