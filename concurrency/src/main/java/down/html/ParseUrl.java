package down.html;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public class ParseUrl {

    private static final String base = "http://www.hao123.com";

    private static final String patternUrl = "<a.*?\\s+href=['\"]([^\"'>]*?)['\"].*?>(.*?)</a>";

    private static LinkedList<String> urls = new LinkedList<String>();

    public synchronized void add(String url) {
        urls.add(url);
    }

    public synchronized String pop() {
        return urls.poll();
    }


    public static void main(String[] args) {


        URLConnection conn;
        try {
            URL url = new URL(base);
            conn = url.openConnection();
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024*1024);
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder charsetDecoder = charset.newDecoder();
            StringBuilder stringBuilder = new StringBuilder();
            while((readableByteChannel.read(byteBuffer))!=-1) {
                byteBuffer.flip();
                CharBuffer charBuffer = charsetDecoder.decode(byteBuffer.asReadOnlyBuffer());
                String s = charBuffer.toString();
                System.out.println("append: " + s);
                stringBuilder.append(s);
                byteBuffer.clear();
            }

            urls = getUrl(stringBuilder.toString());

            System.out.println(urls.size());
            System.out.println(urls);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
        return list;
    }
}
