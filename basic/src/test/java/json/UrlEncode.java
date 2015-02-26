package json;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author ze.liu
 * @since 2014/6/26
 */
public class UrlEncode {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "hello.jsp?name=zhangsan&age=13";

        String s2 = URLEncoder.encode(s,"utf-8");

        System.out.println(s2);

        String s3 = URLDecoder.decode(s2, "utf-8");

        System.out.println(s3);
    }
}
