package patten;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ze.liu on 2014/7/8.
 */
public class PatternTest {

    @Test
    public void test01() {
        Pattern pattern = Pattern.compile("<a.*?>(.*?)</a>");
        String s = "<abbb>12_34</a>";
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

    }

    @Test
    public void test001() {
        Pattern pattern = Pattern.compile("<td.*?>(.*?)</td>");
        String s = "<td class=\"ok\">12_34</td>";
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }

    }

    @Test
    public void test02() {
        Pattern pattern = Pattern.compile("[A-Za-z]+/([A-Za-z]+\\s?)+");
        String s = "WANG%2FYAN";
        Matcher matcher = pattern.matcher(s);
        System.out.println(matcher.find());
    }

    @Test
    public void test03() {
        Pattern pattern = Pattern.compile("[A-Za-z]+/([A-Za-z]+\\s?)+");
        String s = "WANG%2FYAN";
        String s2 = null;
        try {
            s2 = URLDecoder.decode(s, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Matcher matcher = pattern.matcher(s2);
        System.out.println(matcher.find());
    }

    @Test
    public void test04() {
        Pattern p = Pattern.compile("<p>(.*?)</p>");
        Pattern p2 = Pattern.compile("<a.*?>(.*?)</a>");
        //Pattern p = Pattern.compile("<a.*?\\s+href=['\"]([^\"'>]*?)['\"].*?>(.*?)</a>");
        String s = "<p>您已预订相同行程的订单 <a style=\"text_decoration:none\" href=\"/inter/order/detail.do?id=157\">in8140715200309253bb002 </a>，状态为订座成功等待支付，请尽快完成线上支付。</p>";

        Matcher m = p.matcher(s);
        if(m.find()) {
            String s2 = m.group(1);
            System.out.println(s2);
            Matcher m2 = p2.matcher(s2);
            if(m2.find()) {
                System.out.println(m2.group(1));
            }
        }

    }

    @Test
    public void test05() {
        Pattern p = Pattern.compile("<p>(.*?)<a.*?>(.*?)</a>(.*?)</p>");
        Pattern p2 = Pattern.compile("<a.*?>(.*?)</a>");
        //Pattern p = Pattern.compile("<a.*?\\s+href=['\"]([^\"'>]*?)['\"].*?>(.*?)</a>");
        String s = "<p>您已预订相同行程的订单 <a style=\"text_decoration:none\" href=\"/inter/order/detail.do?id=157\">in8140715200309253bb002 </a>，状态为订座成功等待支付，请尽快完成线上支付。</p>";

        Matcher m = p.matcher(s);
        int i = 0;
        while (m.find()) {
            String s2 = m.group(i);
            System.out.println(i+":"+s2);
            i++;
            m.reset();
        }

    }

    @Test
    public void test06() {
        Pattern p = Pattern.compile("<p>(.*?)<a.*?>(.*?)</a>(.*?)</p>");
        //Pattern p = Pattern.compile("<a.*?\\s+href=['\"]([^\"'>]*?)['\"].*?>(.*?)</a>");
        String s = "<p>您已预订相同行程的订单 <a style=\"text_decoration:none\" href=\"/inter/order/detail.do?id=157\">in8140715200309253bb002 </a>，状态为订座成功等待支付，请尽快完成线上支付。</p>";

        Matcher m = p.matcher(s);
        int i = 0;
        while (m.find()) {
            String s2 = m.group(1) + m.group(2) + m.group(3);
            System.out.println(s2);
        }

    }

    @Test
    public void test07() {
        String s = "098765432112345678";
        System.out.println(s.length());
    }

    @Test
    public void test08() {
        String s =  "/*.do";

        String s2 = "/helo.do";
        String s3 = "/hello/ho.do";

        String s4 = "/hello/hhh/hhh.do";

        String[] ss = new String[]{s2, s3, s4};

        Pattern p = Pattern.compile(s);
        for(String tmp:ss) {
            Matcher m = p.matcher(tmp);
            if(m.find()) {
                System.out.println(m.group());
            }
        }

    }

    @Test
    public void test09() {
        String s = "<td class=\"setting-name\">build_Group</td><td class=\"setting-main\"><input readonly=\"true\" name=\"value\" value=\"c\" class=\"setting-input   \" type=\"text\" /></td></tr>";
        //String s = "<td class=\"setting-name\">build_Group</td>";
        //String p ="<td.*?>build_Group.*?name=(.*?)value";
        String p ="<td.*?>bui--ld_Group.*?name=.*?name\"(.*?)\"";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    @Test
    public void test10() {
        String s = "hello ok java";
        System.out.println(s.matches("hello(.*?)"));
    }
}
