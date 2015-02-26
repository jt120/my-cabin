package start;

import com.jt.test.service.Fruit;
import org.junit.Test;

/**
 * @author ze.liu
 * @since 2014/5/28
 */
public class EnumTest {

    @Test
    public void test01() {
        System.out.println(Fruit.APPLE.name());
    }
}
