package json;


import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ze.liu on 2014/7/2.
 */
public class User {
    String name;

    int age;

    String[] interests;

    public User() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", interests=" + Arrays.toString(interests) +
                '}';
    }

    @Test
    public void test1() throws IOException {
        User user = new User();
        user.setName("hh");
        user.setAge(12);
        user.setInterests(new String[] {"football","basketball"});
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("转化Map为字符串--------");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);
        objectMapper.writeValue(System.out, map);
    }

    @Test
    public void test02() {
        User user = new User();
        user.setName("hh");
        user.setAge(12);
        user.setInterests(new String[]{"football", "basketball"});
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("转化Map为字符串--------");
        try {
            String s = objectMapper.writeValueAsString(user);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws IOException {
        String s = "{\"age\":12,\"name\":\"hh\",\"sex\":\"男\",\"interests\":[\"football\"]}";
        String s2 = "{\"name\":\"hh\",\"age\":12,\"interests\":[\"football\",\"basketball\"]}";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        System.out.println("转化Map为字符串--------");
        User u = objectMapper.readValue(s,User.class);
        System.out.println(u);
    }
}
