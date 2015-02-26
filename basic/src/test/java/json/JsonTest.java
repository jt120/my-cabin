package json;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by ze.liu on 2015/1/4.
 */
public class JsonTest {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES);
        mapper.enable(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    @Test
    public void test01() {
        try {
            final User user = mapper.readValue("", User.class);
            System.out.println(user);

        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        float f1 = 0;
        System.out.println(f1);

        float f2 = 0f;
        System.out.println(f2);
    }

    @Test
    public void test03() {
        String s = "14:15/";
        String[] split = s.split("/");
        System.out.println(split.length);
    }
}
