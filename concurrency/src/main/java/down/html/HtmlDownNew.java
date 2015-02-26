package down.html;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public class HtmlDownNew implements Runnable {

    private URLConnection connection;

    private FileChannel fileChannel;

    private static volatile int count;

    private static LinkedList<String> linkedList;

    public static synchronized String pop() {
        String s = linkedList.poll();
        System.out.println("download url: " + s);
        return s;
    }

    public HtmlDownNew(URLConnection connection, FileChannel fileChannel) {
        this.connection = connection;
        this.fileChannel = fileChannel;
        count ++;
    }


    @Override
    public void run() {
        try {
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);
            fileChannel.transferFrom(readableByteChannel, 0, Integer.MAX_VALUE);
            inputStream.close();
            fileChannel.close();
            count --;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String url = "http://www.hao123.com";

        String content = ParseUrlCommon.getHtmlContent(url);
        linkedList = ParseUrlCommon.getUrl(content);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddmmss");
        ;
        String time = simpleDateFormat.format(calendar.getTime());
        System.out.println(time);
        String suffix = ".txt";
        String fileName = time + suffix;
        File file = new File("d:/test/" + fileName);
        String needDown = pop();
        try {
            while(needDown!=null) {
                executorService.submit(new HtmlDownNew(new URL(pop()).openConnection(),
                        new FileOutputStream("d:/test/" + UUID
                                .randomUUID().toString() + ".txt").getChannel
                                ()
                ));
            }

        } catch (Exception e) {
            System.out.println("error url: " + needDown);
            e.printStackTrace();
        }
        executorService.shutdown();

        while(!executorService.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("count num: " + count);
        }
    }
}
