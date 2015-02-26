import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ze.liu on 2014/8/6.
 */
public class TypeTest1 {

    public static void main(String[] args) {
        //Map<String,String[]> map = new HashMap<String,String[]>();
        //map.put("hello",new String[]{"aaa"});
        //
        //String s = ToStringBuilder.reflectionToString(map, ToStringStyle.SHORT_PREFIX_STYLE);
        //System.out.println(s);

    }

    @Test
    public void test01() {
        String s = "8657233940141|e7d5-_e2002c56841_78d51896-_032.432.861.291";
        String d = StringUtils.reverse(s);
        System.out.println(d);
    }
}
