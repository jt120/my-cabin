package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ze.liu
 * @since 2014/6/9
 */
public class FinalMap {

    /**
     * 因为在hashmap中存储的entry，所以最后是entry改变了，并没有改变map，final不影响
     */
    static final Map map = new HashMap();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            map.put(i, "hello " + i);
        }
    }
}
