package down.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public class ParseUrlCommon {

    private static final String base = "http://www.hao123.com";


//    private static final String patternUrl = "<a.*?\\s+href=['\"]([^\"'>]*?)['\"].*?>(.*?)</a>";

    private static final String patternUrl = "<a.*?\\s+href=['\"](http[^\"'>]*?)['\"].*?>(.*?)</a>";

    private static LinkedList<String> urls = new LinkedList<String>();

    public synchronized void add(String url) {
        urls.add(url);
    }

    public synchronized String pop() {
        return urls.poll();
    }


    public static String getHtmlContent(String urlPath) {
        URLConnection conn;
        InputStream inputStream = null;
        try {
            URL url = new URL(urlPath);
            conn = url.openConnection();
            conn.connect();
            inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {


        URLConnection conn;
        InputStream inputStream = null;
        try {
            URL url = new URL(base);
            conn = url.openConnection();
            conn.connect();
            inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String s;
            while((s = bufferedReader.readLine())!=null) {
//                System.out.println("append: " + s);
                stringBuilder.append(s);
            }

            urls = getUrl(stringBuilder.toString());

            System.out.println(urls.size());

            int num = 1;
            for(String sr:urls) {
                System.out.println(num + ":"+sr);
                num++;
            }
//            System.out.println(urls);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static LinkedList<String> getUrl(String s) {
        LinkedList<String> list = new LinkedList<String>();
        Pattern pattern = Pattern.compile(patternUrl);
        Matcher matcher = pattern.matcher(s);
        matcher.find();
        while(matcher.find()) {
            list.add(matcher.group(1));
        }
        System.out.println("list size: " + list.size());
        return list;
    }
}
