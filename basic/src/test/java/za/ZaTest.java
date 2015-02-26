package za;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author ze.liu
 * @since 2014/6/6
 */
public class ZaTest {

    @Test
    public void test01() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(40));
        }
    }

    @Test
    public void test02() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hello", "");
        Object o = map.get("hello");
        if (o.equals("")) {
            System.out.println("yes");
        }
        if (o instanceof String) {
            System.out.println("ok");
        }

        map.put("good", new HashMap());

        Object o2 = map.get("good");
        if (o2 instanceof Map) {
            System.out.println("map");
        }
    }

    @Test
    public void test03() {
        Map<String, Object> map = new HashMap<String, Object>();

        Map<String, String> m1 = new HashMap<String, String>();

        m1.put("1", "a");

        map.put("good", m1);

        Object o2 = map.get("good");
        if (o2 instanceof Map) {
            Map<String, String> m2 = (Map) o2;
            if (!m2.entrySet().isEmpty()) {
                Map.Entry<String, String> entry = m2.entrySet().iterator().next();
                System.out.println(entry.getValue());
            }
        }
    }

    @Test
    public void test04() {
        double pr = 100.0;
        int gPrice = 10;
        int ppr = (pr - gPrice) > 10 ? (int) pr - gPrice : 10;
        System.out.println(ppr);
    }

    @Test
    public void test05() {
        Map<String, Object> map = new HashMap<String, Object>();

        Map<String, String> m1 = null;

        map.put("hello", m1);
        System.out.println(map.get("hello"));
    }

    @Test
    public void test06() {
        Map<String, Object> map = new HashMap<String, Object>();

        Map<String, String> m1 = new HashMap<String, String>();

        map.put("hello", m1);
        System.out.println(map.get("hello"));
    }

    @Test
    public void test07() {
        Person p = new Person();
        System.out.println(p.getName());

        System.out.println(p.getAge());
    }

    @Test
    public void test08() {
        String a = "  bbbccc aaa ";
        System.out.println(StringUtils.isBlank(a));
    }

    @Test
    public void test09() {
        double d = Double.parseDouble("1.1.1");
        System.out.println(d);
    }

    @Test
    public void test11() {
        boolean a = true || getResult();

        System.out.println(a);
    }

    public boolean getResult() {
        System.out.println("result");
        return false;
    }

    @Test
    public void test12() {
        String s = URLDecoder.decode("http://www.qunar.com/booksystem/booking.jsp?d=PyXPvhfTOWfZqEtXTyu9fhFjNZbSdCTdMOsTiY31fxbD1eyIQkMlfQGoatgigO6iZrn0wwHFTvwRY6ZBMbtfPGYm7uAgjnNeXNT2T%2B3iire54T7rS6MVARj1wYjVptUt7z0L%2B3x%2BIzsS57aEbWAAHWK3hQha%2B7TKaAxdZ6VFuVzF59fOLP6vOb17eEj3vPABQT7XjNBixerDIUBYiQqmw0L8yxWfHmp4SSXvXJL%2B3kwwQjVVyQvgjd5utWLzPyqIiaT3X6m%2BWaaPgymbSAbsXGZSHjfPE%2FCZLdD5zD6ELAAWbDKCDhZevanJNgvfi6vI&k=0&bookinginfo=type%3A3%7C%7Cbookingurl%3Ahttps%3A%2F%2Ftc.airfrance.com%2Fc%2F%3Ftcs%3D324%26chn%3DMetasearch%26src%3DQunar%26ctycmp%3DCN%26schtype%3DOrganic%26dev%3DDefault%26url%3Dhttps%3A%2F%2Fwww.airfrance.com%2FCN%2Fzh%2Flocal%2Fprocess%2Fstandardbooking%2FDisplayUpsellAction.do%3Fcabin%3DY%26nbPax%3D1%26from%3DPVG%26to%3DCDG%26outboundDate%3D2014-10-05%26firstOutboundHour%3D23%3A30%26subCabin%3DMCHER%26calendarSearch%3D1%26typeTrip%3D2%26flightOutbound%3DAF0111%26flightInbound%3DAF0116%26partnerName%3DQunar%26partnerCode%3DQU%26inboundDate%3D2014-10-17%26firstInboundHour%3D23%3A25%26listPaxTypo%3DADT%26WT.tsrc%3Dmetasearch%26WT.mc_id%3DC_CN_metasearch_Qunar_organic_website_Null_Null%7C%7Ctimestamp%3A1404789688004%7C%7Cextraurl%3A");
        System.out.println(s);
        System.out.println(s.length());
    }

    @Test
    public void test13() {
        Map<String, String[]> map = new HashMap<String, String[]>();
        map.put("name", new String[]{"zhangsan", "lisi"});
        map.put("age", new String[]{11 + ""});
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            for (String v : entry.getValue()) {
                System.out.print(entry.getKey() + "=" + v + "&");
            }
        }
    }

    private static final String HELLO = "hello";

    @Test
    public void test14() {
        String s = "<p>您已预订相同行程的订单 <a style=\"text_decoration:none\" href=\"/inter/order/detail.do?id=157\">in8140715200309253bb002 </a>，状态为订座成功等待支付，请尽快完成线上支付。</p>";

        System.out.println(s);
    }

    @Test
    public void test15() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","zhang");
        map.put("age","1");
        System.out.println(map+"");
    }




    @Test
    public void test17() {
        String s = "\n" +
                "\n" +
                "\n" +
                "{\"ret\" : false}\n" +
                "\n";
        System.out.println(StringUtils.trimToEmpty(s));
    }

    @Test
    public void test18() {
        String s = "abc";
        s.split("=");
    }

    @Test
    public void test20() {
        String s = "singlepric\n" +
                "e=255&fee=80&flightType=2&priceSrc=COM_ROUND_G_POLICY&supplierName=^_^&supplierContact=^_^&policy_source=COM_ROUND_G_POLICY&policy_id=36&apply=1&iBase\n" +
                "Price=2550.0&iBasePriceCode=&qt=e66b55697f01019&returnTicketCond=有效期内退票费500泰铢（退票请在航班起飞24小时前提出申请，否则不允许退票；机票必须按顺\n" +
                "序使用，否则未使用航段不能退票）&changeDateCond=有效期内同等舱位间允许改期（改期请在航班起飞24小时前提出申请；改期仅限相同舱位相同价格、且在机票有效期\n" +
                "内更改；如改期遇到机票淡旺季发生变化，需另补齐差价）&luggageCond=从曼谷到苏梅岛：20公斤；从苏梅岛到曼谷：20公斤&statement=NOSHOW费为票价的30%。NOSHOW\n" +
                "罚金以航空公司规定为准 说明：如无法搭乘当次航班，请在航班起飞24小时前通知取消记录，否则视为NOSHOW。航空公司如需加收其他服务费，具体费用以航空公司规定\n" +
                "为准。不得签转。其他规定以航空公司规定为准&csingleprice=0&cfee=-1&farebasis=DEFAULT&source=null&devoid=&category=1&postcode=&tgqNote=&record_price=&is\n" +
                "QFee=0&tid=&url=&taxType=0&token=U9G/4MCo6mkAAAA5viSQhcVQrXKd+IBtJ5hxTg==&extras=null&fromTransfer=false&retTransfer=false&policy_code=GTG201405226671\n" +
                "6186365044RT&officialUrl=null&flight_no=TG287&cabin=Y&departure_day=2014-09-29&departure_airport=BKK&departure_time=15:20&arrival_day=2014-09-29&arriv\n" +
                "al_airport=USM&arrival_time=16:25&departure_city=素万那普机场&arrival_city=苏梅岛机场&segment_type=1&plane_type=734&flight_no=TG282&cabin=Y&departure_\n" +
                "day=2014-10-05&departure_airport=USM&departure_time=09:30&arrival_day=2014-10-05&arrival_airport=BKK&arrival_time=10:35&departure_city=苏梅岛机场&arri\n" +
                "val_city=素万那普机场&segment_type=2&plane_type=734\n";
        try {
            String s2 = URLDecoder.decode(s, "utf-8");
            System.out.println(s2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test21() {
        String s = "&diffTaxFee=";
        System.out.println("hello"+s);
    }

    private static String NULL_STRING = "null";

    public static boolean isNotNull(String s) {
        return StringUtils.isNotBlank(s) && !s.equals(NULL_STRING);
    }

    @Test
    public void test16() {
        String s = null;
        String s2 = "null";
        String s3 = "  ";
        //System.out.println(StringUtils.isBlank(s));
        //System.out.println(StringUtils.isBlank(s2));
        //System.out.println(StringUtils.isBlank(s3));
        System.out.println(isNotNull(s2));
        System.out.println(isNotNull(s));
        System.out.println(isNotNull(s3));


        BigDecimal bigDecimal = new BigDecimal("58");
        System.out.println(bigDecimal);
    }

    @Test
    public void test23() {
        String s = "1980-01-02";
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        LocalDate localDate = fmt.parseLocalDate(s);
        //06FEB91
        DateTimeFormatter fmt2 = DateTimeFormat.forPattern("ddMMMyy");
        //System.out.println(localDate.toString(fmt2));
        String s3 = fmt2.withLocale(Locale.GERMANY).print(localDate);
        System.out.println(s3.toUpperCase());
    }

    @Test
    public void test24() {
        String s = "\077";
        System.out.println(s);
    }

    private static final Pattern flightCountFormat2 = Pattern.compile("^[b-n]{4}$");

    @Test
    public void test25() {
        String p = "^[b-nB-N]{4}$";
        String p2 = "^\\d{4}$";
        String s = "bcde";
        String s2 = "bBde";
        String s3 = "aBde";
        String s4 = "1abd";
        s2 = s4;
        s2 = "hello";
        boolean r = !s.matches(p)||!s.matches(p2);
        boolean r2 = !(s.matches(p)||s.matches(p2));
        System.out.println(r);
        System.out.println(r2);
        //System.out.println(s.matches(p));
        //System.out.println(s3.matches(p));
        //System.out.println(s2.matches(p));
        //System.out.println(s4.matches(p));
    }

    @Test
    public void test26() {
        String s = "http://www.sina.com/hello.html?a_b-c~d?name=zh&age= &c=2+3";
        String s1 = null;
        try {
            s1 = URLEncoder.encode(s, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(s1);
    }

    @Test
    public void test27() {
        Random r1 = new Random(10);
        Random r2 = new Random(12);
        System.out.println(r1.nextInt(20));
        System.out.println(r1.nextInt(20));
        System.out.println(r2.nextInt(20));
    }

    @Test
    public void test28() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","zhangsan");
        System.out.println(map);

    }

    @Test
    public void test29() {

        String airline = "QR149";
        int len = airline.length();
        String a = airline.substring(0,2);
        String b = StringUtils.repeat("0", 5-len);
        String c = airline.substring(2);
        System.out.println(a + " " + b + " " + c);
        String n = airline.substring(0, 2) + StringUtils.repeat("0", 5 - len) + airline.substring(2);
        System.out.println(n);
    }

    @Test
    public void test30() {

        String s = "\u0002FSI/PR \u001Bb[\\\\n]S PR   383T11MAR CAN0145 0400MNL0X    320   \u001Bb[\\\\n]S PR  2849T11MAR MNL0900 1015CEB0S    320   \u001Bb[\\\\n]01 YO                   2939 CNY                    INCL TAX\u001Bb[\\\\n]02 TOBEXCN+T*           1019 CNY                    INCL TAX\u001Bb[\\\\n]03 TOBEXCN               909 CNY                    INCL TAX\u001Bb[\\\\n]*SYSTEM DEFAULT-CHECK OPERATING CARRIER \u001Bb[\\\\n]*TRAVEL TAX MAY APPLY VIA PHILIPPINES - CHECK EXEMPTIONS\u001Bb[\\\\n]*PH CHECKIN TAX MAY APPLY. SEE FXT/PH/--\u001Bb[\\\\n]*2*TKT STOCK RESTR  \u001Bb[\\\\n]*ATTN PRICED ON 17SEP14*0009\u001Bb[\\\\n]RFSONLN/1E /EFEP_20/FCC=D/PAGE 1/1  \u001Bb[\\\\n]\u001E\u001Bb\u0003~~~\u0002 CAN   \u001Bb[\\\\n]XMNL TOBEXCN         NVB      NVA11MAR 20K  \u001Bb[\\\\n] CEB TOBEXCN         NVB      NVA11MAR 20K  \u001Bb[\\\\n]FARE  CNY     490   \u001Bb[\\\\n]TAX   CNY     90CN CNY     29LI CNY    300YQ\u001Bb[\\\\n]TOTAL CNY     909   \u001Bb[\\\\n]11MAR15CAN PR X/MNL PR CEB M78.46NUC78.46END ROE6.244500\u001Bb[\\\\n]ENDOS *BUDGET ECONOMY/NONREF\u001Bb[\\\\n]ENDOS *PENALTIES APPLY  \u001Bb[\\\\n]*AUTO BAGGAGE INFORMATION AVAILABLE - SEE FSB   \u001Bb[\\\\n]RFSONLN/1E /EFEP_20/FCC=T/  \u001Bb[\\\\n]\u001E\u001Bb\u0003^^^";
        System.out.println(StringUtils.contains(s, "CAN"));
    }


    @Test
    public void test31() {

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if(i==5) {
                System.out.println("i = 5");
                break;
            }
        }
        System.out.println("end");
    }

}
